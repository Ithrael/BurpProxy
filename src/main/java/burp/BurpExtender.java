package burp;

import burp.utils.OKHttpUtils;
import com.alibaba.fastjson.JSONObject;

import java.awt.Component;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BurpExtender implements IBurpExtender, IHttpListener, IMessageEditorTabFactory {
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private PrintWriter stdout;
    private BurpHelper burpHelper;
    private String matchUrl = "47.112.115.242:8089/sys/login";
    private String decryptReqUrl = "http://127.0.0.1:5000/decryptRep";
    private String decryptRespUrl = "http://127.0.0.1:5000/decryptResp";
    private String decryptReqKey = "username";
    private String decryptRespKey = "msg";
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
        burpHelper = new BurpHelper(helpers);
        stdout = new PrintWriter(callbacks.getStdout(), true);

        // set our extension name
        callbacks.setExtensionName(BURP_PROXY_TAB_NAME);

        callbacks.registerHttpListener(this);
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

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        if (messageIsRequest) {
            burpHelper.ParseRequest(messageInfo);
            String requestUrl = burpHelper.url.toString();
            stdout.println(requestUrl);
            if (requestUrl.contains(matchUrl)) {
                messageInfo.setHighlight("red");
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
            return true;
        }

        @Override
        public void setMessage(byte[] content, boolean isRequest) {
            if (content == null) {
                // clear our display
                txtInput.setText(null);
                txtInput.setEditable(false);
            } else {
                String decryptResp = "";
                if (isRequest) {
                    // 处理请求
                    List<IParameter> parameters = helpers.analyzeRequest(content).getParameters();
                    for (IParameter parameter : parameters) {
                        stdout.println(parameter.getValue() + ":" + parameter.getName());
                    }
                    // retrieve the data parameter
                    IParameter parameter = helpers.getRequestParameter(content, decryptReqKey);
                    stdout.println(parameter.getName() + "---" + parameter.getValue());
                    // deserialize the parameter value
                    try {
                        decryptResp = OKHttpUtils.post(decryptReqUrl, decryptReqKey, parameter.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 处理响应
                    burpHelper.ParseResponse(content);
                    stdout.println("response:");
                    String respBodyStr = new String(burpHelper.responseBody, StandardCharsets.UTF_8);
                    stdout.println(respBodyStr);
                    JSONObject jsonObject = JSONObject.parseObject(respBodyStr);
                    String value = jsonObject.getString(decryptRespKey);
                    try {
                        decryptResp = OKHttpUtils.post(decryptRespUrl, decryptRespKey, value);
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
}