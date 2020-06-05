package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nasazumi
 * @description ┌└ ┐┘
 *  1. 什么是juc
 *      └- java util.concurrent
 *      |        └- Interface Callable<V>
 *      └- java util.concurrent.atomic
 *      └- java util.concurrent.locks
 *      |        └- Interface Lock
 *      └- java util.function
 *       Runable 没有返回值 效率比 Callable 相对较低
 *  2. 进程和线程回顾
 *      并发 多线程操作同一个资源
 *      并行 cpu多核 多个线程同时执行
 *          └- 并发编程本质 充分利用cpu资源
 *  3. lock锁
 *  4. 生产者和消费者
 *  5. 8锁的现象
 *  6. 集合类线程不安全
 *  7. Callable
 *  8. CountDownLatch CyclicBarrier Semaphore
 *  9. 读写锁
 *  10.阻塞队列
 *  11.线程池
 *  12.四大函数式接口
 *  13.Stream流式计算
 *  14.分支合并
 *  15.异步回掉
 *  16.JMM
 *  17.volatlie
 *  18.深入单例模式
 *  19.深入理解CAS
 *  20.原子引入
 *  21.可重入锁 公平锁 非公平锁 自旋锁 死锁
 * @date 2020-06-04
 */
public class JUC {
    /**
     *  传统的synchronzied
     *      └- 线程是一个单独的资源类 没有任何附属操作
     */
    @Test
    public void test() {
        Ticket ticket = new Ticket() ;
        new Thread(() -> {
            for (int i = 1 ; i < 50 ; i ++) {
                ticket.sale();
            }
        }, "A").start();
    }

}

class Ticket {
    private int number = 50 ;

    /**
     *  new NonfairSync() 默认非公平锁
     *      └- 公平锁 十分公平 先来后到
     *      └- 非公平锁 可以插队
     *  Lock
     *    └- 1 new ReentrantLock(0
     *    └- 2 Lock.lock()
     *    └- finally -> lock.unlock()
     *  和Synchronized区别
     *      └- Synchronized是内置的java关键字 Lock是一个类
     *      └- Synchronized无法判断锁的状态, Lock可以判断可以是否获取到了锁
     *      └- Synchronized会自动释放锁，lock必须手动释放锁，不然会死锁
     *      └- Synchronized 线程1 获得锁阻塞，线程2 等待
     *         Lock锁不一定 tryLock()
     *      └- Synchronized 可重入锁，不可以中断，非公平
     *         Lock 可重入锁，可以判断锁 非公平(可以自己设置)
     *      └- Synchronized 适合锁少量代码的同步问题
     *         Lock适合锁大量同步代码
     *   锁
     *   生产者和消费者
     *
     *   面试
     *      └- 单例模式 排序算法 生产者消费者 死锁
     */
    Lock lock = new ReentrantLock() ;

    public void sale() {

        lock.lock(); //加锁
        try{
            if(number > 0) {
                System.out.println(Thread.currentThread().getName() + "剩余票数" + (number --));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}