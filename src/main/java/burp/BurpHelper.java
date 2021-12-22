package burp;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class BurpHelper {
    List<String> requestHeaders = null;
    List<String> responseHeaders = null;
    IExtensionHelpers helpers;
    IRequestInfo requestInfo = null;
    IResponseInfo responseInfo = null;
    List<IParameter> params = null;
    byte[] responseBody = null;
    URL url = null;

    public BurpHelper(IExtensionHelpers _helpers) {
        helpers = _helpers;
    }

    void ParseRequest(IHttpRequestResponse request) {
        requestInfo = helpers.analyzeRequest(request);
        params = requestInfo.getParameters();
        requestHeaders = requestInfo.getHeaders();
        url = requestInfo.getUrl();
    }

    void ParseResponse(byte[] response) {
        responseInfo = helpers.analyzeResponse(response);
        responseHeaders = responseInfo.getHeaders();
        int bodyOffset = responseInfo.getBodyOffset();
        responseBody = Arrays.copyOfRange(response, bodyOffset, response.length);//not length-1
    }

    String getRequestHeaderValue(String name) {
        String v = "";
        if (requestHeaders != null) {
            Iterator<String> it = requestHeaders.iterator();

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


    String getRequestParamValue(String name) {
        String v = "";
        if (params != null) {
            Iterator<IParameter> it = params.iterator();

            IParameter param;
            while (it.hasNext()) {
                param = it.next();
                String paramName = param.getName();
                if (paramName.indexOf(name) != -1) {
                    v = param.getName();
                    break;
                }
            }
        }
        return v;
    }
}
