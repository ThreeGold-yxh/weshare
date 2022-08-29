package com.yxh.weshare.interceptor;

import com.yxh.weshare.annotation.SellerRequired;
import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.pojo.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Xinhao Yi
 * @date 2022/8/4 5:08
 * @description:
 */
public class SellerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前增强的方法是不是HandlerMethod.class
        // 如果不是说明当前请求并不是 SpringMVC 管理，
        // 如果不是再自行根据业务做响应操作，这里直接返回 true
        // Determine if the currently enhanced method is HandlerMethod.class
        // If not, the current request is not managed by SpringMVC.
        // If not, then do the response yourself according to the business, here directly return true
        if (HandlerMethod.class.isInstance(handler)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 判断该 handler 是否有WebLoginRequired注解
            // Determine if the handler has the WebLoginRequired annotation
            SellerRequired sellerRequired = handlerMethod.getMethod().getDeclaredAnnotation(SellerRequired.class);

            // 如果该 handler 没有 AdminRequired注解,就要去看这个handler方法处于的Controller类有没有AdminRequired注解
            // getBeanType() 是拿到这个方法所处的类
            // If the handler does not have the AdminRequired annotation, see if the Controller class the handler method is in has the AdminRequired annotation
            // getBeanType() is to get the class that the method is in
            if (null == sellerRequired) {
                sellerRequired = handlerMethod.getBeanType().getAnnotation(SellerRequired.class);
            }

            //检查有没有loginRequired注解
            // 判断该 handler 是否有WebLoginRequired注解
            // Check for the presence of the loginRequired annotation
            // Determine if the handler has the WebLoginRequired annotation
            WebLoginRequired webLoginRequired = handlerMethod.getMethod().getDeclaredAnnotation(WebLoginRequired.class);

            // 如果该 handler 没有 WebLoginRequired注解,就要去看这个handler方法处于的Controller类有没有WebLoginRequired注解
            // getBeanType() 是拿到这个方法所处的类
            // If the handler does not have the WebLoginRequired annotation, see if the Controller class the handler method is in has the WebLoginRequired annotation
            // getBeanType() is to get the class where the method is located
            if (null == webLoginRequired){
                webLoginRequired = handlerMethod.getBeanType().getAnnotation(WebLoginRequired.class);
            }

            //先尝试获取当前登录状态
            // Try to get the current login status first
            Object userObject = request.getSession().getAttribute("user");
            String adminExecuteURL = sellerRequired.value();

            if ("".equals(adminExecuteURL)){
                adminExecuteURL = "fore/page/access-deny";
            }

            // executeURL = fore/page/login
            String path = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort()
                    + request.getContextPath() + "/"  + adminExecuteURL;


            //如果没有登录,而且没有webLoginRequired注解，直接不允许访问
            //如果有webLoginRequired，前面在webLoginRequired的拦截器中就已经转发给login controller了，不要重复重定向
            //If there is no login, and there is no webLoginRequired annotation, access is not allowed directly
            //If there is a webLoginRequired, it is already forwarded to the login controller in the webLoginRequired interceptor, so don't redirect it again.
            if (null == userObject){
                if (null == webLoginRequired){
                    response.sendRedirect(path);
                }
                return true;
            }else {
                // 检查登陆账户的权限，如果等于4，就不允许进入
                // Check the permissions of the login account, if it is equal to 4, access is not allowed
                User user = (User) userObject;
                if (4 == user.getAuthority()){
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
