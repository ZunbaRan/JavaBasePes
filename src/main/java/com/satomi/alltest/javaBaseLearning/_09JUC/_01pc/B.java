package com.satomi.alltest.javaBaseLearning._09JUC._01pc;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nasazumi
 * @description
 *      线程之间的通信问题: 生产者和消费者问题
 *      线程交替执行 A B 操作同一个变量 num = 0
 *      A num + 1
 *      B num - 1
 * @date 2020-06-04
 */
public class B {
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

    @Test
    public void test(){
        Data data = new Data() ;
        new Thread(() -> {
            for (int i = 0 ; i < 10 ; i ++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}, "A").start();

        new Thread(() -> {
            for (int i = 0 ; i < 10 ; i ++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}, "B").start();
    }

}

// 等待 业务 通知
class Data1{
    //数字 资源类
    private int num = 0 ;

    Lock lock = new ReentrantLock() ;
    Condition condition = lock.newCondition();

    public  void increment() throws InterruptedException {
        try{
            lock.lock();
            while(num != 0) {
                condition.await();
            }
            num ++ ;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            // 通知其他线程 +1 完毕类
            condition.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public  void decrement() throws InterruptedException {
        try{
            lock.lock();
            while(num == 0) {
                condition.await();
            }
            num -- ;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            // 通知其他线程 -1 完毕
            condition.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
