package com.yxh.weshare.utils;

/**
 * @author Xinhao Yi
 * @date 2022/7/27 20:25
 * @description:
 */
public class ClassUtils {
    public static String testPath(){
        return ClassUtils.class.getClassLoader().getResource("").getPath();
    }
}
