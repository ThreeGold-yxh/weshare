package com.yxh.weshare.aspect;

import com.yxh.weshare.bean.pojo.Goods;
import com.yxh.weshare.bean.pojo.User;
import com.yxh.weshare.service.UserService;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Xinhao Yi
 * @date 2022/7/30 14:56
 * @description:
 */
@Component
@Aspect
@Order(2)
public class AddHistoryAspectJ {

    // goodsDetailShowAspectJ

    @Autowired
    UserService userService;

    //注意Spring中拦截器interceptor总是先于aop切面执行的
    // Note that the interceptor interceptor in Spring is always executed before the aop facet

    //定位方法，切入点  Positioning methods, entry points
    @Pointcut("execution(public * com.yxh.weshare.controller.GoodsController.goodsDetailShow(..))")
    public void goodsDetailShowAspectJ(){

    }

    //强化切面方法 把当前用户点击的商品加入到history中
    // Enhanced faceted approach Adds items clicked by the current user to the history
    @SneakyThrows
    @Around("goodsDetailShowAspectJ()")
    public Object addGoodsUserClickedToHistory(ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if (args.length != 3){
            //如果参数个数不是三个，也就是不是三个形参，说明增强的函数错了，直接放行
            // If the number of arguments is not three, i.e. not three formal parameters, it means that the enhanced function is wrong, so just let it go
            Object proceed= joinPoint.proceed();
        }

        //准备把这个goodsId和userId还有生成的一个createtime，丢进history中
        // Prepare to drop this goodsId and userId, along with a createtime generated, into the history
        // 如果这个人没有登录，我们直接放行，不管它
        // If the person is not logged in, we just let it go, regardless

        /* 获取当前user */
        // Get current user
        // todo 下面这句注释掉的话有大问题，就是这个拿user才导致了重定向到登录页面报错！！！！！要改写法
        //  There is a big problem with the following commented out sentence, it is this take user that causes the redirect to the login page to report an error ！！！！！ To change the way you write
        // User user = (User)request.getSession().getAttribute("user");

        //AOP中获取request 方法 参考：https://www.jianshu.com/p/0725629a1694
        // HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        //AOP 中获取session方法
        // Get session method in AOP
        HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);

        // Object obj = request.getSession().getAttribute("user");
        Object obj = session.getAttribute("user");
        if (obj == null){
            //如果还没登录。就不记录了，直接放行
            Object proceed= joinPoint.proceed();
            return proceed;
        }

        //如果已经登录了，就要开始记录了
        // If you are already logged in, you need to start recording

        /* 获取当前user */
        //  Get current user
        User user = (User) obj;

        Integer goodsId = (Integer) args[0];

        Goods goods = new Goods();
        goods.setWsGoodsId(goodsId);

        Boolean isAdd = userService.addGoodsToHistory(user, goods);

        if (!isAdd){
            System.out.println("fail to add the goods to history, please check the source code");
        }

        //放行
        // Release
        Object proceed= joinPoint.proceed();
        //注意这里一定要return 这个结果值，不然原本的方法中的返回值会丢失的！！！
        //Note that you must return the result value here,
        //otherwise the return value of the original method will be lost!
        return proceed;
    }
}
