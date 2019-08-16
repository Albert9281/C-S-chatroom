package com.pys.java.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*资源文件的加载*/
public class CommUtils {
    private static final Gson GSON = new GsonBuilder().create();

    public static Properties loadProperties(String fileName){
        Properties properties = new Properties();
        InputStream in = CommUtils.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(in);
        } catch (IOException e) {
            return null;
        }
        return properties;
    }
    /*序列化（对象-->json）*/
    public static String object2Json(Object obj) {
        return GSON.toJson(obj);
    }
    /*反序列化（json-->对象）*/
    public static Object json2Object(String jsonStr, Class objClass) {
        return GSON.fromJson(jsonStr,objClass);
    }

}
