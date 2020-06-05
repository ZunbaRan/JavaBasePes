package com.satomi.alltest.javaBaseLearning._01thread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-01
 */

class Window1 implements Runnable {

    private int ticket = 100 ;

    /**
     * 1 实例化ReentrantLock
     * 2 调用lock.lock()
     * 3 关闭lock.unlock()
     *
     *  synchronized 和 lock
     *      都是处理线程安全的方式
     *      synchronized执行完同步代码块自动释放同步监视器
     *      lock手动启动释放锁
     *
     *  wait() 执行此方法当前线程进入阻塞状态，并释放同步监视器
     *  notify() 唤醒一个被wait的线程，如果有多个线程wait则唤醒优先级高的
     *  notifyAll() 唤醒所有被wait的线程
     *  三个方法必须使用在同步代码块或者同步方法中
     *  调用者必须是同步监视器，否则会出现IllegalMonitorStateExecption
     *  三个方法是定义在java.lang.Object
     *
     *  sleep() 和 wait() 方法的异同
     *  1 相同点：
     *      都可以使当前线程进入阻塞状态
     *  2 不同点：
     *      两个方法声明的位置不同
     *          Thread类中声明sleep()
     *          Object类中声明wait()
     *      调用要求不同
     *          sleep()可以在任何场景下调用
     *          wait()只能在同步代码块中调用
     *      是否释放同步监视器
     *          两个方法都在同步代码块或同步方法中
     *          sleep()不会释放同步监视器
     *          wait()会释放锁
     */

    //true 先进先出
    private ReentrantLock lock = new ReentrantLock(true) ;

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            try {
                lock.lock();

                if (ticket > 0) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + ticket);
                    ticket -- ;
                } else {
                    break;
                }

            } finally {
                lock.unlock();
            }

        }
    }
}

public class _8LockTest {
    public static void main(String[] args) {
        Window1 w = new Window1() ;

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
    }
}
