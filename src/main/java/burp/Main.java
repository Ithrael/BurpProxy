package burp;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("47.112.115.242:8089.*?");
        boolean flag = pattern.matcher("http://47.112.115.242:8089/sys/login").find();
        System.out.println(flag);
    }
}
