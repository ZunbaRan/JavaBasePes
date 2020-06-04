package com.satomi.alltest.javaBaseLearning.thread;

/**
 * @author nasazumi
 * @description 同步机制将单例模式重的懒汉式改为线程安全的
 * @date 2020-06-01
 */

public class _4LazySingle {
}

class Bank {
    private Bank() {}

    private static Bank instance = null ;


    /**
     * 效率不高
     * 第二次进入方法只为了获取实例对象，但是依然要进行同步判断
     */
    //加入sychronized
    private static synchronized Bank getInstance() {
        if(instance == null) {
            /**
             * 进入这一步进行cpu时间片切换，进入阻塞
             * 此时另一个线程进入此方法
             * 就会实例出两个对象
             */
            instance = new Bank() ;
        }
        return instance ;
        /**
         *        synchronized (Bank.class) {
         *            if(instance == null) {
         *                instance = new Bank() ;
         *            }
         *            return instance ;
         *         }
         */

    }
}