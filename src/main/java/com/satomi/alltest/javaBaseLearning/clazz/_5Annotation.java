package com.satomi.alltest.javaBaseLearning.clazz;

import org.junit.Test;

import java.lang.annotation.*;

/**
 * @author nasazumi
 * @description
 *      框架 = 注解 + 反射 + 设计模式
 *  annotation就是代码里的的特殊标记，标记可以在编译，类加载，运行时被读取
 *  1 文档相关
 *  2 格式检查
 *      @Override
 *      @Deprecated
 *      @SuppressWarnings
 *  3 跟踪代码依赖性，实现替代配置文件的功能
 *      框架
 *  4 元注解
 *      用来修饰其他Annotation定义
 *      Retention
 *          |-- 指明修饰Annotation的生命周期
 *          |-- RetentionPolicy的枚举类,包含三种生命周期状态
 *                      |-- SOURCE 编译后不会保存
 *                      |-- CLASS (默认行为)编译时候有，运行时不会保留 not be retained by the VM at run time
 *                      |-- RUNTIME 保留在class文件里，运行时加载到内存里，可以通过反射读取
 *      Target
 *          |-- 用于指定被修饰的annotation能用于修饰哪些程序元素
 *      Documented
 *      Inherited
 * |----
 *     @Target(ElementType.METHOD)
 *     @Retention(RetentionPolicy.SOURCE)
 *     public @Interface Override
 *
 *    元数据
 *    元数据  元数据    真实数据
 *      |      |        |
 *    String name = "nabailan"
 *
 *  5 通过反射获取注解信息
 * @date 2020-06-02
 */
public class _5Annotation {

    @Test
    public void testGetAnnotation() {
        Class clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();
    }

}

/**
 * 自定义注解
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() ;
}

@MyAnnotation(value = "hi")
class Person{
    private String name ;
}
