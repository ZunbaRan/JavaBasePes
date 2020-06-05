package com.satomi.alltest.javaBaseLearning._09JUC._02lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-04
 */
public class lock8_03 {

    /**
     *  send call都是静态方法，是先执行发send还是先call
     *    └- 先发短信
     *    └- 原因: static 静态方法，类一加载就有了
     *                    └- 锁的是Phone3 Class模板对象
     *  new 两个对象分别调用send call
     *     └- 先发短信，原理同上，加载到内存中的Phone3运行时类对象只有一个
     *
     *  send是静态方法 call是普通同步方法
     *     └- 先发短信
     *     └- 原因: 两个方法的锁不同，一个是Phone3的运行时类对象，一个是Phone3的实例对象
     */
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3() ;
        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone1.call();
        },"B").start();
    }
}

class Phone3{
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    public static synchronized void call(){
        System.out.println("call");
    }
    // 不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
