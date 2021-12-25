package burp.entity;

import burp.ui.RuleDialog;

public class Rule {
    private String matchUrl;
    private String decryptRequestUrl;
    private String decryptResponseUrl;
    private Color color;

    private static class RuleInstance {
        private static final Rule instance = new Rule();
    }

    public static Rule getInstance(){
        return Rule.RuleInstance.instance;
    }

    public Rule(){

    }

    public Rule(String matchUrl, String decryptRequestUrl, String decryptResponseUrl, Color color) {
        this.matchUrl = matchUrl;
        this.decryptRequestUrl = decryptRequestUrl;
        this.decryptResponseUrl = decryptResponseUrl;
        this.color = color;
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


    public String getDecryptResponseUrl() {
        return decryptResponseUrl;
    }

    public void setDecryptResponseUrl(String decryptResponseUrl) {
        this.decryptResponseUrl = decryptResponseUrl;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
