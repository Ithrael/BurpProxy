package burp;

import java.awt.Component;
import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender, IMessageEditorTabFactory {
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private PrintWriter stdout;
    private String host = "47.112.115.242:8089/sys/login";
    private String decryptUrl = "http://127.0.0.1:5000/decrypt";
    private final String BURP_PROXY_TAB_NAME = "burp decrypt proxy";

    //
    // implement IBurpExtender
    //

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        // keep a reference to our callbacks object
        this.callbacks = callbacks;

        // obtain an extension helpers object
        helpers = callbacks.getHelpers();
        stdout = new PrintWriter(callbacks.getStdout(), true);

        // set our extension name
        callbacks.setExtensionName(BURP_PROXY_TAB_NAME);

        // register ourselves as a message editor tab factory
        callbacks.registerMessageEditorTabFactory(this);
    }

    //
    // implement IMessageEditorTabFactory
    //

    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable) {
        // create a new instance of our custom editor tab
        return new BurpDecrptProxyTab(controller, editable);
    }

    //
    // class implementing IMessageEditorTab
    //

    class BurpDecrptProxyTab implements IMessageEditorTab {
        private boolean editable;
        private ITextEditor txtInput;
        private byte[] currentMessage;

        public BurpDecrptProxyTab(IMessageEditorController controller, boolean editable) {
            this.editable = editable;

            // create an instance of Burp's text editor, to display our deserialized data
            txtInput = callbacks.createTextEditor();
            txtInput.setEditable(editable);
        }

        //
        // implement IMessageEditorTab
        //

        @Override
        public String getTabCaption() {
            return BURP_PROXY_TAB_NAME;
        }

        @Override
        public Component getUiComponent() {
            return txtInput.getComponent();
        }

        @Override
        public boolean isEnabled(byte[] content, boolean isRequest) {
            // enable this tab for requests containing a data parameter
//            String requestUrl = helpers.analyzeRequest(content).getUrl().toString();
            stdout.println(helpers.analyzeRequest(content).getHeaders());
            return true;
        }

        @Override
        public void setMessage(byte[] content, boolean isRequest) {
            if (content == null) {
                // clear our display
                txtInput.setText(null);
                txtInput.setEditable(false);
            } else {
                // retrieve the data parameter
                IParameter parameter = helpers.getRequestParameter(content, "data");

                // deserialize the parameter value
                txtInput.setText(helpers.base64Decode(helpers.urlDecode(parameter.getValue())));
                txtInput.setEditable(editable);
            }

            // remember the displayed content
            currentMessage = content;
        }

        @Override
        public byte[] getMessage() {
            // determine whether the user modified the deserialized data
            if (txtInput.isTextModified()) {
                // reserialize the data
                byte[] text = txtInput.getText();
                String input = helpers.urlEncode(helpers.base64Encode(text));

                // update the request with the new parameter value
                return helpers.updateParameter(currentMessage, helpers.buildParameter("data", input, IParameter.PARAM_BODY));
            } else return currentMessage;
        }

        @Override
        public boolean isModified() {
            return txtInput.isTextModified();
        }

        @Override
        public byte[] getSelectedData() {
            return txtInput.getSelectedText();
        }
    }
}