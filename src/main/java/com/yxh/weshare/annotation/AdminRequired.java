package com.yxh.weshare.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xinhao Yi
 * @date 2022/8/3 4:59
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface AdminRequired {
    String value() default "admin/access-deny"; // 未登录时需要跳转的路径 Path to jump to when not logged in
}
