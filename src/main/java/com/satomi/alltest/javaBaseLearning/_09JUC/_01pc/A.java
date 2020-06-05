package com.satomi.alltest.javaBaseLearning._09JUC._01pc;

import org.junit.Test;

/**
 * @author nasazumi
 * @description
 *      线程之间的通信问题: 生产者和消费者问题
 *      线程交替执行 A B 操作同一个变量 num = 0
 *      A num + 1
 *      B num - 1
 * @date 2020-06-04
 */
public class A {
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
class Data{
    //数字 资源类
    private int num = 0 ;
    public synchronized void increment() throws InterruptedException {
       while(num != 0) {
           this.wait();
       }
       num ++ ;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        // 通知其他线程 +1 完毕类
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(num == 0) {
            this.wait();
        }
        num -- ;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        // 通知其他线程 -1 完毕
        this.notifyAll();
    }
}
