package burp.entity;

public enum Color {
    RED("red"), GREEN("green"), BLANK("blank"), YELLOW("yellow");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}