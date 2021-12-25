package burp;

import burp.entity.Color;
import burp.ui.BackGround;
import burp.ui.RuleDialog;
import burp.utils.OKHttpUtils;
import burp.utils.StdoutUtils;

import javax.swing.*;
import java.awt.Component;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class BurpExtender implements IBurpExtender, IHttpListener, IMessageEditorTabFactory, ITab {
    private final BackGround back = new BackGround(null, null);
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private BurpHelper burpHelper;
    private PrintWriter stdout;
    private Pattern patternUrl = Pattern.compile("47.112.115.242:8089.*?");
    private String decryptReqUrl = "http://127.0.0.1:5000/decryptReq";
    private String decryptRespUrl = "http://127.0.0.1:5000/decryptResp";
    private final String BURP_PROXY_TAB_NAME = "DecryptProxy";

    //
    // implement IBurpExtender
    //

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        // obtain an extension helpers object
        helpers = callbacks.getHelpers();
        burpHelper = new BurpHelper(helpers);

        StdoutUtils.stdout = new PrintWriter(callbacks.getStdout(), true);
        stdout = StdoutUtils.stdout;
        stdout.println("@Core Author: Ithrael");
        stdout.println("@UI Author: Ithrael");
        stdout.println("@Github: https://github.com/Ithrael/BurpProxy");

        // set our extension name
        callbacks.setExtensionName(BURP_PROXY_TAB_NAME);

        // UI
        SwingUtilities.invokeLater(this::initialize);

        callbacks.registerHttpListener(this);
        callbacks.registerMessageEditorTabFactory(this);
    }

    private void initialize() {
        callbacks.customizeUiComponent(back);
        callbacks.addSuiteTab(BurpExtender.this);
    }

    @Override
    public String getTabCaption() {
        return BURP_PROXY_TAB_NAME;
    }

    @Override
    public Component getUiComponent() {
        return back;
    }

    public boolean isMatch(String url) {
        return patternUrl.matcher(url).find();
    }

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        if (messageIsRequest) {
            burpHelper.ParseRequest(messageInfo);
            String requestUrl = burpHelper.url.toString();
            if (isMatch(requestUrl)) {
                messageInfo.setHighlight(Color.RED.getValue());
            }
        }
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
            return "decrypt proxy";
        }

        @Override
        public Component getUiComponent() {
            return txtInput.getComponent();
        }

        @Override
        public boolean isEnabled(byte[] content, boolean isRequest) {
            // enable this tab for requests containing a data parameter
//            String requestUrl = helpers.analyzeRequest(content).getUrl().toString();
            return true;
        }

        @Override
        public void setMessage(byte[] content, boolean isRequest) {
            if (content == null) {
                txtInput.setText(null);
                txtInput.setEditable(false);
            } else {
                String decryptResp = "";
                if (isRequest) {
                    // 处理请求
                    String contentReqStr = new String(content, StandardCharsets.UTF_8);
                    stdout.println("request contentStr: " + contentReqStr);
                    try {
                        decryptResp = OKHttpUtils.post(decryptReqUrl, Constants.RequestKey, contentReqStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    // 处理响应
                    String contentRespStr = new String(content, StandardCharsets.UTF_8);
                    stdout.println("response contentStr:" + contentRespStr);
                    try {
                        decryptResp = OKHttpUtils.post(decryptRespUrl, Constants.ResponseKey, contentRespStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                txtInput.setText(decryptResp.getBytes(StandardCharsets.UTF_8));
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

    //
    // implement IMessageEditorTabFactory
    //

    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable) {
        // create a new instance of our custom editor tab
        return new BurpDecrptProxyTab(controller, editable);
    }
}