package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-04
 */
public class _05Latch {

    /**
     * 辅助类
     * └- CountDownLatch 减法计数器
     * └- CyclicBarrier  加法计数器
     * └- Semaphore      信号量  存疑
     * └- ReadWriteLock 更加细化的锁
     *      private ReadWriteLock readWriteLock = new ReentrantReadWriteLock() ;
     *          └- put() 写的时候只有一个人能写
     *              └- readWriteLock.writeLock().lock()
     *              └- readWriteLock.writeLock().unlock()
     *          └- get() 读的时候所有人都能读
     *              └- readWriteLock.readLock().lock()
     *              └- readWriteLock.readLock().unlock()
     */

    @Test
    public void test() throws InterruptedException {
        //总数是6 必须要执行任务的适合再使用
        CountDownLatch countDownLatch = new CountDownLatch(6) ;
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Go to");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        /**
         * 每次有线程调用countDownLatch，假设计数器变为零，await()就会被唤醒
         */
        countDownLatch.await(); //等待计数器归零，再向下执行
    }

    @Test
    public void test1() {
        /**
         * 计数到7个线程就会开启一个线程执行 召唤神龙
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功");
        }) ;
        for (int i = 1; i <= 7 ; i++) {
            /**
             * final修饰符对变量来说，深层次的理解就是保障变量值的一致性
             * 如果不用final修饰，则原先的局部变量可以发生变化。
             * 如果局部变量发生变化后，匿名内部类是不知道的
             * 因为他只是拷贝了局部变量的值，并不是直接使用的局部变量
             */
            final int temp = i ;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @Test
    public void test03() {
        //线程数量 停车位
        Semaphore semaphore = new Semaphore(3) ;

            for (int i = 1; i <= 6 ; i++) {
                System.out.println(i);
                final int temp = i ;
                new Thread(() -> {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "抢到车位");
                        //TimeUnit.SECONDS.sleep(2);
                        for (int j = 0; j < 100; j++) {
                            System.out.println("i"+ temp + "j" + j);
                        }
                        System.out.println(Thread.currentThread().getName() + "离开车位");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }, String.valueOf(i)).start();
            }
    }

}
