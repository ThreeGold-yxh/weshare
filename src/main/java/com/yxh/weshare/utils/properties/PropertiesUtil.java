package com.yxh.weshare.utils.properties;

import com.yxh.weshare.utils.alert.AlertUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Xinhao Yi
 * @date 2022/7/26 14:00
 * @description:
 */
public class PropertiesUtil {
    private static Properties urlProperties;
    static {
        InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("url.properties");
        urlProperties = new Properties();
        try {
            urlProperties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getURL(String key){
        return urlProperties.getProperty(key);
    }


}
