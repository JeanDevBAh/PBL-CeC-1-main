package network;

import com.google.gson.Gson;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static String paraJson(Object objeto){
        return gson.toJson(objeto);
    }

    public static <T> T paraObject(String json, Class<T> classe){
        return gson.fromJson(json, classe);
    }
}
