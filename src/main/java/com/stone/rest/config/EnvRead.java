package com.stone.rest.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author guocang.shi
 */
public class EnvRead{

    private static String url;
    private static String key;
    private static String value;

    private static String user;
    private static String password;
    private static  String uattoken;


    static {
        String envkey="dev";
        String keyValue="dev";
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/config.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(envkey.equals(prop.getProperty(keyValue))) {
            url = prop.getProperty("SIT_URL").trim();
        }
        else
        {
            url = prop.getProperty("UAT_URL").trim();
        }
        key = prop.getProperty("APIKEY").trim();
        value= prop.getProperty("TOKE").trim();
        user = prop.getProperty("USER").trim();
        password= prop.getProperty("PASSWORD").trim();
        uattoken = prop.getProperty("UAT_TOKEN").trim();
    }
    //获取url

    public static String getUrl() {
        return url;
    }
    //获取key

    public static String getKey() {
        return key;
    }
    //获取value

    public static String getValue() {
        return value;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getuattoken() {
        return uattoken;
    }
}
