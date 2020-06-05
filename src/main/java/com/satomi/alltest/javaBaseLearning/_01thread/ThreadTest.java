package com.satomi.alltest.javaBaseLearning._01thread;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-01
 */
public class ThreadTest {
    /**
     * 1 创建一个继承与thread类的子类
     * 2 重写Thread的run（）
     * 3 创建子类的对象
     * 4 通过此对象调用start（）
     */
    public static void main(String[] args) {
        //主线程造对象
        Mythread t1 = new Mythread() ;
        //主线程调用start方法
        t1.start(); //启动当前线程， 调用当前线程的run（）
        // start() --> private native void start0()
        /**
         * Causes this thread to begin execution ;
         * the Java Virtual Machine calls the run method of this thread
         */
        //t1.run(); run没有激活线程
    }
}


