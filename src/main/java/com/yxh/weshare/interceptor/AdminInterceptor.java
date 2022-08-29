package com.yxh.weshare.interceptor;

import com.yxh.weshare.annotation.AdminOrSupporterRequired;
import com.yxh.weshare.annotation.AdminRequired;
import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.pojo.User;
import com.yxh.weshare.utils.properties.PropertiesUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Xinhao Yi
 * @date 2022/8/3 4:59
 * @description:
 */
public class AdminInterceptor implements HandlerInterceptor {
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
            AdminRequired adminRequired = handlerMethod.getMethod().getDeclaredAnnotation(AdminRequired.class);

            // 如果该 handler 没有 AdminRequired注解,就要去看这个handler方法处于的Controller类有没有AdminRequired注解
            // getBeanType() 是拿到这个方法所处的类
            // If the handler does not have the AdminRequired annotation,
            // see if the Controller class the handler method is in has the AdminRequired annotation
            if (null == adminRequired){
                adminRequired = handlerMethod.getBeanType().getAnnotation(AdminRequired.class);
            }

            AdminOrSupporterRequired adminOrSupporterRequired = handlerMethod.getMethod().getDeclaredAnnotation(AdminOrSupporterRequired.class);

            // 如果该 handler 没有 AdminOrSupporterRequired注解,就要去看这个handler方法处于的Controller类有没有AdminOrSupporterRequired注解
            // getBeanType() 是拿到这个方法所处的类

            // If the handler does not have the AdminOrSupporterRequired annotation, look at the Controller class the handler method is in to see if it has the AdminOrSupporterRequired annotation.
            // getBeanType() is to get the class that the method is in
            if (null == adminOrSupporterRequired){
                adminOrSupporterRequired = handlerMethod.getBeanType().getAnnotation(AdminOrSupporterRequired.class);
            }

            //我们默认AdminRequired是优先级更高的注解，也就是如果你两个注解都打上去了的话，默认只有AdminRequired生效
            // 如果上面两步后,发现是有注解的,那么就要看登录状态,和登陆账户的权限，都没有注解就直接放行了
            //先尝试获取当前登录状态

            // By default, AdminRequired is the higher priority comment, which means that if you type in both comments, only AdminRequired will take effect by default.
            // If you find that there is a comment after the above two steps, then you should look at the login status and the permissions of the login account, and let it go if there is no comment
            // Try to get the current login status first
            Object userObject = request.getSession().getAttribute("user");
            if (null != adminRequired){
                String adminExecuteURL = adminRequired.value();

                if ("".equals(adminExecuteURL)){
                    adminExecuteURL = "admin/access-deny";
                }

                // executeURL = fore/page/login
                String path = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort()
                        + request.getContextPath() + "/"  + adminExecuteURL;

                //如果没有登录,直接不允许访问
                // If not logged in, access will be denied directly
                if (null == userObject){
                    response.sendRedirect(path);
                    return true;
                }else {
                    // 检查登陆账户的权限，如果不为1，就不允许进入
                    // Check the permissions of the login account, if it is not 1, access is not allowed
                    User user = (User) userObject;
                    if (1 != user.getAuthority()){
                        response.sendRedirect(path);
                        return true;
                    }
                    //
                }
            }
            // adminRequired == null
            else if (null != adminOrSupporterRequired){
                String adminOrSupporterExecuteURL = adminOrSupporterRequired.value();
                if ("".equals(adminOrSupporterExecuteURL)){
                    adminOrSupporterExecuteURL = "admin/access-deny";
                }

                // executeURL = fore/page/login
                String path = request.getScheme() + "://" + request.getServerName()
                        + ":" + request.getServerPort()
                        + request.getContextPath() + "/"  + adminOrSupporterExecuteURL;
                //如果没有登录,直接不允许访问
                // If not logged in, access will be denied directly
                if (null == userObject){
                    response.sendRedirect(path);
                    return true;
                }else {
                    // 检查登陆账户的权限，如果不为1 且 不为2，就不允许进入
                    // Check the permissions of the login account, if it is not 1 and not 2, access is not allowed
                    User user = (User) userObject;
                    if (1 != user.getAuthority() && 2 != user.getAuthority()){
                        response.sendRedirect(path);
                        return true;
                    }
                }
            }
        }
        //放行  release
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
