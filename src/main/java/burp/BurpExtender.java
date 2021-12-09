package burp;

import burp.utils.OKHttpUtils;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BurpExtender implements IBurpExtender, IHttpListener {
    private IBurpExtenderCallbacks callbacks;
    private PrintWriter stdout;
    BurpHelper burpHelper;
    private String host = "47.112.115.242:8089/sys/login";
    private String decryptUrl = "http://127.0.0.1:5000/decrypt";

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        callbacks.setExtensionName("Burp Proxy");
        callbacks.printOutput("load success");
        this.callbacks = callbacks;
        // obtain our output stream
        stdout = new PrintWriter(callbacks.getStdout(), true);

        // register ourselves as an HTTP listener
        callbacks.registerHttpListener(this);
        burpHelper = new BurpHelper(callbacks.getHelpers());
    }

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
        if (messageIsRequest) {
            burpHelper.ParseRequest(messageInfo);
//            if (!burpHelper.url.toString().contains(host)) {
//                return;
//            }
//            List<String> reqHeaders = burpHelper.headers;
//            stdout.println("request url: " + burpHelper.url);
//            stdout.println("request header: ");
//            for (String header : reqHeaders) {
//                stdout.println(header);
//            }
//
//            String contentType = burpHelper.getHeaderValue("Content-Type");
//            stdout.println("Content-Type: " + contentType);
//
//            String key = "password";
//            String value = burpHelper.getParamValue(key);
//
//            messageInfo.setHighlight("blue");
//            messageInfo.setComment(value);

//            messageInfo.setRequest("fsdfsfdsfds".getBytes(StandardCharsets.UTF_8));
//            try {
//                String res = OKHttpUtils.post(decryptUrl, key, value);
//                messageInfo.setRequest("fsdfsfdsfds".getBytes(StandardCharsets.UTF_8));
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        } else {
            burpHelper.ParseResponse(messageInfo.getResponse());
            if (!burpHelper.url.toString().contains(host)) {
                return;
            }
            stdout.println("response url: " + burpHelper.url);
            if (!burpHelper.url.toString().contains(host)) {
                return;
            }
            stdout.println("response url: " + burpHelper.url);
            String responseBody = new String(burpHelper.responseBody, StandardCharsets.UTF_8);

            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            String msg = jsonObject.getString("msg");
            try {
                String decryptBody = OKHttpUtils.post("http://127.0.0.1:5000/decrypt", "msg", msg);
                jsonObject.put("msg", decryptBody);
                stdout.println("decrypt: ");
                stdout.println("msg: " + decryptBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
