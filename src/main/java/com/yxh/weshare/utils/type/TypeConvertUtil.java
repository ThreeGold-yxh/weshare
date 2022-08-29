package com.yxh.weshare.utils.type;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author Xinhao Yi
 * @date 2022/7/30 18:41
 * @description:
 */
public class TypeConvertUtil {

    private TypeConvertUtil(){}

    public static String convertTimeStampToString(Timestamp timestamp){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
    }
}
