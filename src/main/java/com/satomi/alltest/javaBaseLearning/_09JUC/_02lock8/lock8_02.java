package com.satomi.alltest.javaBaseLearning._09JUC._02lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-04
 */
public class lock8_02 {

    /**
     *  增加了一个普通方法，是先执行发短信还是hello
     *    └- 先hello
     *    └- 原因: 普通方法不受锁的影响
     *  new两个Phone phone1 sendSms phone2 call
     *    └- 先call
     *    └- 原因: 两个调用者两把锁
     *
     */
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2() ;
        Phone2 phone2 = new Phone2() ;
        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        },"B").start();
    }
}

class Phone2{
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }
    public synchronized void call(){
        System.out.println("call");
    }
    // 不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
