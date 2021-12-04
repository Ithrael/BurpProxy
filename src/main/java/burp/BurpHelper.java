package burp;

import java.util.Iterator;
import java.util.List;

class BurpHelper {
    List<String> headers = null;
    IExtensionHelpers helpers;
    IRequestInfo info = null;

    public BurpHelper(IExtensionHelpers _helpers) {
        helpers = _helpers;
    }

    void Parse(IHttpRequestResponse request) {
        info = helpers.analyzeRequest(request);
        headers = info.getHeaders();
    }

    String getHeaderValue(String name) {
        String v = "";
        if (headers != null) {
            Iterator<String> it = headers.iterator();

            while (it.hasNext()) {
                String header = it.next();
                if (header.indexOf(name) != -1) {
                    String[] pv = header.split("[:]");
                    if (pv.length > 1) {
                        v = pv[1].trim();
                        break;
                    }
                }
            }
        }
        return v;
    }
}
