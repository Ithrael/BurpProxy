package burp;

public class BurpExtender implements IBurpExtender{
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        callbacks.setExtensionName("Burp Proxy");
        callbacks.printOutput("load success");
    }
}
