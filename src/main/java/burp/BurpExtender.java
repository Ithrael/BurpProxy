package burp;

import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

public class BurpExtender implements IBurpExtender, IHttpListener {
    private IBurpExtenderCallbacks callbacks;
    private PrintWriter stdout;
    BurpHelper burpHelper;

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
            burpHelper.Parse(messageInfo);
            List<String> reqHeaders = burpHelper.headers;
            stdout.println("request header: ");
            for (String header : reqHeaders) {
                stdout.println(header);
            }

//            burpHelper.getHeaderValue("")
        } else {

        }
    }
}
