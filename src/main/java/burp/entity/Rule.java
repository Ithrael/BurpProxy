package burp.entity;

public class Rule {
    private String matchUrl;
    private String decryptRequestUrl;
    private String requestKey;
    private String decryptResponseUrl;
    private String responseKey;

    public Rule(String matchUrl, String decryptRequestUrl, String requestKey, String decryptResponseUrl, String responseKey) {
        this.matchUrl = matchUrl;
        this.decryptRequestUrl = decryptRequestUrl;
        this.requestKey = requestKey;
        this.decryptResponseUrl = decryptResponseUrl;
        this.responseKey = responseKey;
    }

    public String getMatchUrl() {
        return matchUrl;
    }

    public void setMatchUrl(String matchUrl) {
        this.matchUrl = matchUrl;
    }

    public String getDecryptRequestUrl() {
        return decryptRequestUrl;
    }

    public void setDecryptRequestUrl(String decryptRequestUrl) {
        this.decryptRequestUrl = decryptRequestUrl;
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }

    public String getDecryptResponseUrl() {
        return decryptResponseUrl;
    }

    public void setDecryptResponseUrl(String decryptResponseUrl) {
        this.decryptResponseUrl = decryptResponseUrl;
    }

    public String getResponseKey() {
        return responseKey;
    }

    public void setResponseKey(String responseKey) {
        this.responseKey = responseKey;
    }
}
