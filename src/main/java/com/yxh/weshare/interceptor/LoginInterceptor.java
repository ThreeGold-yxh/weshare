package com.yxh.weshare.interceptor;

import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.utils.alert.AlertUtil;
import com.yxh.weshare.utils.properties.PropertiesUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  SpringMVC中的Interceptor拦截器是链式的，可以同时存在 多个Interceptor，
 *  然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
 *  Controller方法调用之前调用。
 *  SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
 *  回值为false，
 *  当preHandle的返回值为false的时候整个请求就结束了。
 *
 */

/**
 * @author Xinhao Yi
 * @date 2022/7/26 11:25
 * @description: 使用方法,在需要登陆验证的方法或者Controller类前面加上  @WebLoginRequired  注解
 * Use the @WebLoginRequired annotation in front of the method or controller class that requires login authentication.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前增强的方法是不是HandlerMethod.class
        // 如果不是说明当前请求并不是 SpringMVC 管理，
        // 如果不是再自行根据业务做响应操作，这里直接返回 true

        // Determine if the currently enhanced method is HandlerMethod.class
        // If not, the current request is not managed by SpringMVC.
        // If not, then do the response yourself according to the business, here directly return true
        if (HandlerMethod.class.isInstance(handler)){
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 判断该 handler 是否有WebLoginRequired注解
            // Determine if the handler has the WebLoginRequired annotation
            WebLoginRequired webLoginRequired = handlerMethod.getMethod().getDeclaredAnnotation(WebLoginRequired.class);

            // 如果该 handler 没有 WebLoginRequired注解,就要去看这个handler方法处于的Controller类有没有WebLoginRequired注解
            // getBeanType() 是拿到这个方法所处的类
            // If the handler does not have the WebLoginRequired annotation, see if the Controller class the handler method is in has the WebLoginRequired annotation
            // getBeanType() is to get the class where the method is located
            if (null == webLoginRequired){
                webLoginRequired = handlerMethod.getBeanType().getAnnotation(WebLoginRequired.class);
            }

            // 如果上面两步后,发现是有注解的,那么就要看登录状态,没有注解直接放行了
            // If there is an annotation after the above two steps, then look at the login status, and let it go without the annotation
            if (null != webLoginRequired){
                //如果没有登录,就要重定向到登录页面,否则就放行
                // If not logged in, redirect to the login page, otherwise let it go
                if (null == request.getSession().getAttribute("user")){
                    String executeURL = webLoginRequired.value();
                    if ("".equals(executeURL)){
                        executeURL = PropertiesUtil.getURL("login");
                    }

                    // 然后重定向到登录界面，注意这里重定向到登录，是不允许被UrlInterceptor拦截的
                    // 否则，我们之前的url就变成登录的url了，就失去了保存原有位置的功能
                    // then redirect to the login screen, note that here the redirect to login is not allowed to be intercepted by UrlInterceptor
                    // Otherwise, our previous url will become the login url and we will lose the ability to save the original location

                    // executeURL = fore/page/login
                    String path = request.getScheme() + "://" + request.getServerName()
                            + ":" + request.getServerPort()
                            + request.getContextPath() + "/"  + executeURL;


                    String tempTest = request.getContextPath() + "/" + executeURL;
                    System.out.println(tempTest);

                    response.sendRedirect(path);
                    return true;
                }
            }
        }
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
