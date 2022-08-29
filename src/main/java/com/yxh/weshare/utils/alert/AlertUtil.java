package com.yxh.weshare.utils.alert;

import com.yxh.weshare.utils.recommend.RecommendCalculateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author Xinhao Yi
 * @date 2022/7/25 3:00
 * @description: 在后端校验出错时，直接让前端弹出警告弹窗  Direct front-end warning pop-ups in case of back-end checksum errors
 */
public class AlertUtil {

    private static Properties properties;
    static {
        InputStream resourceAsStream = AlertUtil.class.getClassLoader().getResourceAsStream("url.properties");
        properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void alert(HttpServletResponse response, HttpServletRequest request, String nameOfView, String alertMsg) throws IOException {
        //fixme 这里还不确定对不对
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String applicationPath = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + applicationPath + "/";

        String url = properties.getProperty(nameOfView);
//        String res = "<script language=\"javascript\">alert('" + alertMsg + "');window.location.href='" + basePath + url + "'</script>";
        String res = "<script language=\"javascript\">alert('" + alertMsg + "');window.location.href='" + applicationPath + "/" + url + "'</script>";
//        String res = "<script language=\"javascript\">alert('" + alertMsg + "')</script>";
        System.out.println(res);

        //"weshare是我的工程名
        out.print(res);
        out.flush();
//        out.print("<script language=\"javascript\">alert('test!');window.location.href='/weshare/fore/page/register'</script>");

    }

}
