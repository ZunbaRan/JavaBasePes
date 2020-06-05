package com.satomi.alltest.javaBaseLearning._04generic;

import org.junit.Test;
import org.omg.CORBA.Object;

import java.util.List;

/**
 * @author nasazumi
 * @description
 *  1 集合中使用范型
 *      范型的类型必须是类，不能是基本数据结构，要用到基本数据类型的位置，使用对应的包装类
 *  2   范型类
 *      范型接口
 *      范型方法
 *  3 泛型在继承方面的体现
 *      Class A 是 Class B 的父类
 *      G<A> 和 G<B> 二者不具备父子关系，二者是并列关系
 *  4 通配符
 *      <?> 作为公共父类
 *      有限制条件的通配符
 *
 * @date 2020-06-02
 */
public class _01GenericTest {
    @Test
    public void test01() {
        /**
         * 实例化没有指明范型 则认为是Object类型
         */
        Order order = new Order() ;
    }

    @Test
    public void test02() {
        List<Object> list1 = null ;
        List<String> list2 = null ;

        /**
         *  对于List<?> 就不能向其内部添加数据，除了null
         *      list.add(null)
         *  允许读取数据，读取的数据类型为Object
         */
        List<?> list = null ;
        list = list1 ;
        list = list2 ;
    }

    /**
     * 有限制条件的通配符的使用
     *  ? extends Person
     *  ? super Person
     */
    @Test
    public void test03() {
        /**
         *  相当于≤
         *  ? extends A
         *      可以作为G<A> 和 G<B> 的父类，其中B extends A
         */
        List<? extends Person> list1 = null ;
        /**
         *  相当于≥
         *  ? super A
         *      可以作为G<A> 和 G<B> 的父类，其中A extends B
         */
        List<? super Person> list2 = null ;

        List<Student> list3 = null ;
        List<Person> list4 = null ;
        List<Object> list5 = null ;


    }
}

/**
 * 范型类
 * @param <T>
 */
class Order<T> {
    String orderName ;
    int orderId ;

    //类的内部结构就可以使用类的范型
    T orderT ;

    //声明构造器不加<E>
    public Order(){} ;

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT ;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }
}
