package com.yxh.weshare.aspect;

import com.yxh.weshare.bean.pojo.Goods;
import com.yxh.weshare.bean.pojo.User;
import com.yxh.weshare.bean.pojo.UserGoodsScore;
import com.yxh.weshare.service.RecommendService;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author Xinhao Yi
 * @date 2022/8/5 14:20
 * @description:
 */
@Component
@Aspect
@Order(3)
public class ReccomandAspectJ {

    @Autowired
    RecommendService recommendService;

    //定位方法，切入点
    // Positioning methods, entry points
    @Pointcut("execution(public * com.yxh.weshare.controller.GoodsController.goodsDetailShow(..)) " +
            "|| execution(public * com.yxh.weshare.controller.UserController.addGoodsToTracker(..)) " +
            "|| execution(public * com.yxh.weshare.controller.BuyerController.buyerOrderAdd(..))")
    public void reccomandBehaviourScoreAspectJ() {

    }

    //强化切面方法 根据当前用户点击的商品/收藏的商品/购买的商品 添加相应的分数
    // Enhanced cutaway method Adds a score based on the items the current user has clicked on/favored/bought
    @SneakyThrows
    @Around("reccomandBehaviourScoreAspectJ()")
    public Object addGoodsUserClickedToHistory(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //AOP 中获取session方法
        // Get session method in AOP
        HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);

        // Object obj = request.getSession().getAttribute("user");
        Object obj = session.getAttribute("user");
        if (obj == null) {
            //如果还没登录。就不记录了，直接放行
            // If not yet logged in. It will not be logged and will be released directly
            Object proceed = joinPoint.proceed();
            return proceed;
        }

        //如果已经登录了，就要开始记录了 If you are already logged in, you need to start recording

        /* 获取当前user */
        // Get current user
        User user = (User) obj;

        //获取 goodsId
        Integer goodsId = (Integer) args[0];

        // 找到当前访问方法的全限定类名
        // Find the fully qualified class name of the currently accessed method
        String classAndMethodName = getClassAndMethodName(getTargetMethod(joinPoint));
        System.out.println(classAndMethodName);

        Integer addPoints = 0;

        if (classAndMethodName.contains("goodsDetailShow")) {
            addPoints = 1;
        } else if (classAndMethodName.contains("addGoodsToTracker")) {
            addPoints = 2;
        } else if (classAndMethodName.contains("buyerOrderAdd")) {
            addPoints = 3;
        }

        UserGoodsScore userGoodsScore = new UserGoodsScore();
        userGoodsScore.setWsUserId(user.getWsUserId());
        userGoodsScore.setWsGoodsId(goodsId);

        UserGoodsScore userGoodsScoreSelected = recommendService.selectUserGoodsScoreByPrimaryKey(userGoodsScore);


        //没有找到userGoodsScoreSelected，说明是第一次访问这个项目，创建新的数据插入
        // No userGoodsScoreSelected found,
        // indicating that this is the first time the item has been accessed, create a new data insert
        if (userGoodsScoreSelected == null) {

            userGoodsScoreSelected = new UserGoodsScore();
            userGoodsScoreSelected.setWsUserId(user.getWsUserId());
            userGoodsScoreSelected.setWsGoodsId(goodsId);
            userGoodsScoreSelected.setWsScore(addPoints);
        } else {
            //找到了的话，更新分数
            //todo 这里修改一下，分数不允许无限增加，用户对一个物品的最高分就是3分
            // Modify here so that the score is not allowed to increase indefinitely and the maximum score a user can have for an item is 3
            // Update scores if found

            Integer newScore = userGoodsScoreSelected.getWsScore() + addPoints;
            if (newScore > 3){
                userGoodsScoreSelected.setWsScore(3);
            }else {
                userGoodsScoreSelected.setWsScore(newScore);
            }
        }

        Boolean isAdd = recommendService.addUserGoodsScoreToTable(userGoodsScoreSelected);

        if (isAdd) {
            System.out.println("It's the first time for current user to access this goods");
            System.out.println("add scores to user-goods-score table successfully");
        } else {
            System.out.println("fail to add scores to user-goods-score table");
        }

        //放行 Release
        Object proceed = joinPoint.proceed();
        //注意这里一定要return 这个结果值，不然原本的方法中的返回值会丢失的！！！
        // Note that you must return the result value here, otherwise the return value of the original method will be lost!
        return proceed;


    }

    // 基于连接点信息获取目标方法对象
    private Method getTargetMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        //获取目标类对象
        // Get the target class object
        Class<?> aClass = joinPoint.getTarget().getClass();
        //获取方法签名信息,方法名和参数列表
        // Get method signature information, method name and parameter list
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取目标方法对象
        // Get the target method object
        Method method = aClass.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        return method;
    }

    //获取方法类全名+方法名
    // Get method class full name + method name
    private String getClassAndMethodName(Method method) {
        //获取类全名
        // Get the full name of the class
        String className = method.getDeclaringClass().getName();
        //获取方法名
        // Get method name
        String methodName = method.getName();
        return new StringBuffer(className).append(".").append(methodName).toString();
    }


}
