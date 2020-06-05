package com.satomi.alltest.javaBaseLearning._09JUC._01pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-04
 */
public class C {
    /**
     *  生产者和消费者
     *      └- Sychronized
     *          └- 线程可以唤醒而不被通知，中断 或超时，即所谓的虚假唤醒
     *          └- 等待应该总是会出现在循环中
     *              └- if -> while 防止虚假唤醒
     *      └- JUC
     *          └- Sychronized notify wait
     *          └- Lock Condition
     *                      └- signal await
     *                  └- 问题 随机的状态
     *                  └- 精准的通知可唤醒线程
     */

    /**
     *  精准的通知可唤醒线程
     */
    public static void main(String[] args) {
        Data3 data3 = new Data3() ;
        new Thread(() -> {
            for(int i = 0 ; i < 10 ; i++){
                data3.printA();
            }
        },"A").start();
        new Thread(() -> {
            for(int i = 0 ; i < 10 ; i++){
                data3.printB();
            }
        },"B").start();
        new Thread(() -> {
            for(int i = 0 ; i < 10 ; i++){
                data3.printC();
            }
        },"C").start();
    }
}

class Data3{
    private Lock lock = new ReentrantLock() ;
    private Condition condition1 = lock.newCondition() ;
    private Condition condition2 = lock.newCondition() ;
    private Condition condition3 = lock.newCondition() ;
    private int number = 1 ;

    public void printA(){
        lock.lock();
        try {
            //业务 判断 -> 执行 -> 通知
            while ((number != 1)) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "->AAA");
            number = 2 ;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            //业务 判断 -> 执行 -> 通知
            while ((number != 2)) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "->BBB");
            number = 3 ;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            //业务 判断 -> 执行 -> 通知
            while ((number != 3)) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "->CCC");
            number = 1 ;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}