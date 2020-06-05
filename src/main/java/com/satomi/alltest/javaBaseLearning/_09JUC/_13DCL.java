package com.satomi.alltest.javaBaseLearning._09JUC;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author nasazumi
 * @description ┌└ ┐┘ └-
 * @date 2020-06-05
 */
public class _13DCL {
    /**
     * 反射
     */
    public static void main(String[] args) throws Exception {
        //生成对象
        //Lazy instance1 = Lazy.getInstance();
        Constructor<Lazy> declaredConstructor = Lazy.class.getDeclaredConstructor();
        /**
         * 破解私有构造器反射一个新对象
         */
        declaredConstructor.setAccessible(true);
        /**
         * 用私有构造器反射生成两个变量
         */
        Lazy instance = declaredConstructor.newInstance();
        //Lazy instance2 = declaredConstructor.newInstance();
        /**
         * 获取标志位
         * 通过反射改变标志位的值
         */
        Field lili = Lazy.class.getDeclaredField("lili");
        lili.setAccessible(true);
        lili.set(instance, false) ;
    }
}

class Hungry {
    /**
     *  饿汉式单例
     *   可能会浪费空间
     */

//    private byte[] data1 = new byte[1024 * 1024] ;
//    private byte[] data2 = new byte[1024 * 1024] ;
//    private byte[] data3 = new byte[1024 * 1024] ;
//    private byte[] data4 = new byte[1024 * 1024] ;

    private Hungry(){

    }
    private final static Hungry hungry = new Hungry() ;
    public static Hungry getInstance(){
        return hungry ;
    }
}

class Lazy {
    // 标志位，如果没有反编译找不到这个标志位
    private static boolean lili = false ;

    private Lazy() {
        synchronized (Lazy.class){
//            if(null != lazy) {
//                throw new RuntimeException("不要使用反射破坏单例") ;
//            }
            if(lili == false) {
                lili = true ;
            }else {
                throw new RuntimeException("不要使用反射破坏单例") ;
            }
        }
    }

    /**
     * 加 voliatile的原因是因为 new Lzay() 不是原子性操作
     */
    private volatile static Lazy lazy ;

    public static Lazy getInstance() {
        /**
         *  DCL懒汉式
         */
        if( null == lazy) {
            synchronized (Lazy.class) {
                if(lazy == null) {
                    /**
                     *  不是原子性操作
                     *    └- 1 分配内存空间
                     *    └- 2 执行构造方法，初始化对象
                     *    └- 3 把对象指向这个空间
                     *    指令重排 A 132
                     *           B 123
                     *              └- 此时Lazy还没有完成构造
                     */
                    Lazy lazy = new Lazy() ;
                }
            }
        }
        return lazy ;
    }
}

class Holder {
    private Holder(){

    }
    public static Holder getInstance() {
        return InnerClass.HOLDER ;
    }
    public static class InnerClass {
        private static final Holder HOLDER = new Holder() ;
    }
}