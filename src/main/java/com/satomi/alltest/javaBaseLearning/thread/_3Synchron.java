package com.satomi.alltest.javaBaseLearning.thread;

/**
 * 同步机制来解决线程安全问题
 *      同步代码: 操作共享数据的代码
 *      共享数据: 多个线程共同操作的变量
 *      同步监视器: 锁
 *          要求: 多个线程必须要用同一把锁
 * 方式一 同步代码块
 *      synchronized(同步监视器){
 *          //需要被同步的代码
 *      }
 * 方式二 同步方法
 *
 * 操作同步代码时，只能有一个线程参与，其他线程等待，相当于一个单线程
 */
class Synn implements Runnable{

    private int ticket = 100 ;
    Object obj = new Object() ;

    @Override
    public void run() {
        while (true) {
            synchronized(obj) {
                if(ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    ticket -- ;
                } else {
                    break;
                }
            }
        }
    }
}

public class _3Synchron {
    public static void main(String[] args) {
        Synn n = new Synn() ;

        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n);
        Thread t3 = new Thread(n);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

