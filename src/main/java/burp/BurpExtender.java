package burp;

import burp.utils.OKHttpUtils;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class BurpExtender implements IBurpExtender, IMessageEditorTabFactory {
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private PrintWriter stdout;
    BurpHelper burpHelper;
    private String host = "47.112.115.242:8089/sys/login";
    private String decryptUrl = "http://127.0.0.1:5000/decrypt";
    private final String BURP_PROXY_TAB_NAME = "burp decrypt proxy";

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        callbacks.setExtensionName("Burp Proxy");
        callbacks.printOutput("load success");
        this.callbacks = callbacks;
        // obtain our output stream
        stdout = new PrintWriter(callbacks.getStdout(), true);

        // register ourselves as an HTTP listener
        burpHelper = new BurpHelper(callbacks.getHelpers());
        helpers = burpHelper.helpers;
    }

    //
    // implement IMessageEditorTabFactory
    //

    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable)
    {
        // create a new instance of our custom editor tab
        return new BurpDecrptProxyTab(controller, editable);
    }

    //
    // class implementing IMessageEditorTab
    //

    class BurpDecrptProxyTab implements IMessageEditorTab
    {
        private boolean editable;
        private ITextEditor txtInput;
        private byte[] currentMessage;

        public BurpDecrptProxyTab(IMessageEditorController controller, boolean editable)
        {
            this.editable = editable;

            // create an instance of Burp's text editor, to display our deserialized data
            txtInput = callbacks.createTextEditor();
            txtInput.setEditable(editable);
        }

        //
        // implement IMessageEditorTab
        //

        @Override
        public String getTabCaption()
        {
            return BURP_PROXY_TAB_NAME;
        }

        @Override
        public Component getUiComponent()
        {
            return txtInput.getComponent();
        }

        @Override
        public boolean isEnabled(byte[] content, boolean isRequest)
        {
            // enable this tab for requests containing a data parameter
            stdout.println(helpers.analyzeRequest(content).getUrl().toString().contains(host));
            return isRequest && helpers.analyzeRequest(content).getUrl().toString().contains(host);
        }

        @Override
        public void setMessage(byte[] content, boolean isRequest)
        {
            if (content == null)
            {
                // clear our display
                txtInput.setText(null);
                txtInput.setEditable(false);
            }
            else
            {
                // retrieve the data parameter
                IParameter parameter = helpers.getRequestParameter(content, "username");
                stdout.println(parameter.getValue());
                String value = parameter.getValue();
                String decryptBody = "test decryptBody";
                try {
                    decryptBody = OKHttpUtils.post(decryptUrl, "msg", value);
                    stdout.println("decrypt: ");
                    stdout.println("msg: " + decryptBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // deserialize the parameter value
                txtInput.setText(decryptBody.getBytes(StandardCharsets.UTF_8));
                txtInput.setEditable(editable);
            }

            // remember the displayed content
            currentMessage = content;
        }

        @Override
        public byte[] getMessage()
        {
            // determine whether the user modified the deserialized data
            if (txtInput.isTextModified())
            {
                // reserialize the data
                byte[] text = txtInput.getText();
                String input = text.toString();

                // update the request with the new parameter value
                return helpers.updateParameter(currentMessage, helpers.buildParameter("msg", input, IParameter.PARAM_BODY));
            }
            else return currentMessage;
        }

        @Override
        public boolean isModified()
        {
            return txtInput.isTextModified();
        }

        @Override
        public byte[] getSelectedData()
        {
            return txtInput.getSelectedText();
        }
    }
}
