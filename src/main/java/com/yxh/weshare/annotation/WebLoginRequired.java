package com.yxh.weshare.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xinhao Yi
 * @date 2022/7/26 13:37
 * @description: 登录拦截注解
 */

/**
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 *
 * 这3个生命周期分别对应于：Java源文件(.java文件) ---> .class文件 ---> 内存中的字节码。
 *
 * 那怎么来选择合适的注解生命周期呢？
 *
 * 首先要明确生命周期长度 SOURCE < CLASS < RUNTIME ，所以前者能作用的地方后者一定也能作用。
 * 一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解；如果要在编译时进行一些预处理操作，
 * 比如生成一些辅助代码（如 ButterKnife），就用 CLASS注解；如果只是做一些检查性的操作，
 * 比如 @Override 和 @SuppressWarnings，则可选用 SOURCE 注解。
 */


/**
 * 　@Target 说明了Annotation所修饰的对象范围：
 * Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）
 * 、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）
 * 。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
 */

/**
 * 1. RetentionPolicy.SOURCE: annotations are retained in the source file only, and are discarded when the Java file is compiled into the class file.
 * 2. RetentionPolicy.CLASS: annotations are retained in the class file, but are discarded when the class file is loaded by jvm, which is the default lifecycle.
 * 3. RetentionPolicy.RUNTIME: annotations are not only kept in the class file, but remain there after the class file is loaded by jvm.
 *
 * These 3 life cycles correspond to: Java source file (.java file) --> .class file --> bytecode in memory.
 *
 * So how do you choose the right annotation lifecycle?
 *
 * The first thing to make clear is that the lifecycle length SOURCE < CLASS < RUNTIME, so where the former works the latter must also work.
 * Generally, if you need to get annotation information dynamically at runtime, you can only use RUNTIME annotations.
 * If you want to do some pre-processing at compile time, such as generating helper code (e.g. ButterKnife), use the CLASS annotation; if you just want to do some checking
 * If you want to do some preprocessing at compile time, * such as generating helper code (e.g. ButterKnife), use the CLASS annotation.
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface WebLoginRequired {
    String value() default "fore/page/login"; // 未登录时需要跳转的路径 Path to jump to when not logged in
}
