package burp.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OKHttpUtils {
    OKHttpUtils() {
    }

    static OkHttpClient getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private final OkHttpClient singleton;

        Singleton() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5L, TimeUnit.SECONDS);
            builder.readTimeout(5L, TimeUnit.SECONDS);
            builder.writeTimeout(5L, TimeUnit.SECONDS);
            ConnectionPool connectionPool = new ConnectionPool(50, 60, TimeUnit.SECONDS);
            builder.connectionPool(connectionPool);
            singleton = builder.build();
        }

        public OkHttpClient getInstance() {
            return singleton;
        }
    }

    public static String post(String url, String key, String value) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add(key, value)
                .build();
        Request request = new Request.Builder()
                .url(url).post(formBody)
                .build();
        Response response = OKHttpUtils.getInstance().newCall(request).execute();
        return response.body().string();
    }
//    public static void main(String[] args) {
//        OKHttpUtils httpUtils = new OKHttpUtils();
//        String url = "http://127.0.0.1:5000/decrypt";
//        String key = "key";
//        String value = "value";
//        try {
//            String resBody = httpUtils.post(url, key, value);
//            System.out.println(resBody);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}


