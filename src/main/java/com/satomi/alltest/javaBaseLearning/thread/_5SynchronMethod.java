package com.satomi.alltest.javaBaseLearning.thread;

/**
 * 方式二 同步方法
 */
class Synn2 implements Runnable{

    private int ticket = 100 ;
    Object obj = new Object() ;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private synchronized void show() { //同步监视器 this

                if(ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "ticket" + ticket);
                    ticket -- ;
       }
    }
}

public class _5SynchronMethod {
    public static void main(String[] args) {
        Synn2 n = new Synn2() ;

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

