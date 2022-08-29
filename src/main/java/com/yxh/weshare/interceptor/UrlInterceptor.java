package com.yxh.weshare.interceptor;

import com.yxh.weshare.bean.pojo.User;
import com.yxh.weshare.utils.url.UrlUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Xinhao Yi
 * @date 2022/7/29 11:32
 * @description:
 */
public class UrlInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            //先拿到session
            HttpSession session = request.getSession(true);

//            request.getRequestURL() 返回全路径 http://localhost:8080/jqueryLearn/resources/request.jsp
//            request.getRequestURI() 返回除去host（域名或者ip）部分的路径 /jqueryLearn/resources/request.jsp
//            request.getContextPath() 返回工程名部分，如果工程映射为/，此处返回则为空 /jqueryLearn
//            request.getServletPath() 返回除去host和工程名部分的路径 /resources/request.jsp

// request.getRequestURL() returns the full path http://localhost:8080/jqueryLearn/resources/request.jsp
// request.getRequestURI() returns the path without the host (domain or ip) part /jqueryLearn/resources/request.jsp
// request.getContextPath() returns the project name part, or null if the project map is / /jqueryLearn
// request.getServletPath() returns the path without the host and project name parts /resources/request.jsp

            // /weshare/fore/page/login
            String uri = request.getRequestURI();//拿到上一个页面地址   /jqueryLearn/resources/request.jsp

            //其实login不会触发这个配置器了，因为已经在拦截器里配置了忽略login了，可以去SpringMvc.xml里看看
            //如果uri以login结尾，直接放行，不允许修改previouspath
            //in fact login won't trigger this configurator anymore, because it's already configured in the interceptor to ignore login, you can check it in SpringMvc.xml
            //If the uri ends in login, let it go directly, no previouspath changes allowed
            if (uri != null && uri.endsWith("login")) {
                return true;
            }


            // substring (a)是从第a个字符开始截取，包含第a个字符
//            String path = uri.substring(request.getContextPath().length());//去掉项目地址长度的字符（因为我的默认项目地址是给出的）
//        String query = request.getQueryString();//得到参数

//        if (query == null) {
//            query = "";
//        } else {
//            query = "?" + query;
//        }

//        String previouspath = uri + query;
            String previouspath = UrlUtils.getCurrentComleteURL(request);

            System.out.println("URL Interceptor: " + previouspath);

            System.out.println(previouspath);

            session.setAttribute("previouspath", previouspath);

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
