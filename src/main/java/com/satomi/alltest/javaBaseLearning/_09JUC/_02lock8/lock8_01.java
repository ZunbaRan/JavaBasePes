package com.satomi.alltest.javaBaseLearning._09JUC._02lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-04
 */
public class lock8_01 {

    /**
     *  A 在存在sleep情况下 还是闲sendSms 再 call
     *  原因 两个对象拿到同一个锁Phone
     */
    public static void main(String[] args) {
        Phone phone = new Phone() ;
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        },"B").start();
    }
}

class Phone{
    public synchronized void sendSms(){
        System.out.println("sendSms");
    }
    public synchronized void call(){
        System.out.println("call");
    }
}
