package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author nasazumi
 * @description
 *  函数式接口
 *      └- lambda表达式
 *              └- 函数式接口可以用lambda表达式简化
 *      └- 链式编程
 *      └- 函数式接口
 *      └- stream流式计算
 *
 *
 *    └- Function
 *            └- 函数式接口，有一个输入参数，一个输出参数
 *    └- Predicate
 *            └- 断定型接口 有一个输入参数，返回值只能是boolean
 *    └- Supplier
 *            └- 供给型接口 没有输入，只有返回值
 *    └- Consumer
 *            └- 消费型接口 只有输入，没有返回值
 *
 * @date 2020-06-05
 */
public class _08Function {

    @Test
    public void test01(){
        //输出输入的值
        Function function = new Function<String, String>() {
            @Override
            public String apply(String o) {
                return o;
            }
        };
        System.out.println(function.apply("asd"));

        // lambda 简化
        Function function1 = (str) -> {return str;} ;
        System.out.println(function1.apply("str1"));
    }

    @Test
    public void test02(){
        //判断字符长是否为空
        Predicate predicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {
                return o.isEmpty();
            }
        } ;
        System.out.println(predicate.test(""));
    }
}
