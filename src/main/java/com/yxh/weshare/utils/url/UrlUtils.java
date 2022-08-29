package com.yxh.weshare.utils.url;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xinhao Yi
 * @date 2022/7/28 18:52
 * @description:
 */
public class UrlUtils {
    //一定要用getContextPath 而不是直接用 getServletPath 因为后者没有应用名
    public static String getCurrentComleteURL(HttpServletRequest request) {
        String url = "";
        url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getContextPath() + request.getServletPath();
        //拿问号后面的内容
        if (request.getQueryString() != null) {
            url += "?" + request.getQueryString();
        }

        return url;
    }
}
