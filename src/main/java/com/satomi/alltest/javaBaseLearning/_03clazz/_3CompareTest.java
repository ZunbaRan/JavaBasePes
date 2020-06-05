package com.satomi.alltest.javaBaseLearning._03clazz;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author nasazumi
 * @description
 * java中的对象正常情况下只能比较 == 或 !=
 * 不能使用> <
 * 如何实现
 *      Comparable
 *      Comparator
 */
public class _3CompareTest {

    /**
     * 像String 包装类实现了Comparable接口
     * 重写了CompareTo()方法
     *      重写CompareTo()的规则：
     *      如果当前对象this大于形参obj，则返回正整数
     *      如果当前对象this小于形参obj，则返回负整数
     *      如果当前对象this等于形参obj，则返回0
     */
    @Test
    public void test1(){
        String[] arr = new String[]{"AA", "CC", "KK", "MM", "GG"} ;
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Comparable 自然排序
     * 对自定义类来说需要排序
     * 让自定义类实现Comparable接口
     * 重写compareTo()方法
     * compareTo(obj)方法中指明如何排序
     */
    @Test
    public void test02(){
        Goods[] arr = new Goods[4] ;
        arr[0] = new Goods("Kendrick_Lamar", 34) ;
        arr[1] = new Goods("Bob_Dylan", 43) ;
        arr[2] = new Goods("Kanye_West", 12) ;
        arr[3] = new Goods("Guns_N'Rose", 65) ;
    }

    /**
     * comparator 定制排序
     * 重写compare(Object obj1, Object obj2)
     */
    @Test
    public void test03() {
        String[] arr = new String[]{"AA", "CC", "KK", "MM", "GG"} ;
        Arrays.sort(arr, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String) {
                    String s1 = (String) o1 ;
                    String s2 = (String) o2 ;
                    System.out.println(s1.compareTo(s2));
                    return s1.compareTo(s2) ;
                }
                throw new RuntimeException("类型不一致");
            }
        });
        System.out.println(Arrays.toString(arr));
    }
}

class Goods implements Comparable{
    private String name ;
    private double price ;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Goods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        //方式一
        if(o instanceof Goods) {
            Goods goods = (Goods)o ;
            if(this.price > goods.price) {
                return 1 ;
            }else if(this.price < goods.price) {
                return -1 ;
            }else {
                return 0 ;
            }
            //方式二
            //return Double.compare(this.price, goods.price) ;
        }
        throw new RuntimeException("传入的数据类型不符合");
    }
}
