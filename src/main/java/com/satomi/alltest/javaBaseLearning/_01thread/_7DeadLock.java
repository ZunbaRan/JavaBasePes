package com.satomi.alltest.javaBaseLearning._01thread;


import lombok.SneakyThrows;

/**
 * 解决死锁
 *      专门的算法 原则
 *      尽量减少同步资源的定义
 *      尽量避免嵌套同步
 */
public class _7DeadLock {

    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer() ;
        StringBuffer s2 = new StringBuffer() ;

        new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a") ;
                    s2.append("1") ;

                    sleep(100);
                    synchronized (s2) {
                        s1.append("b") ;
                        s2.append("2") ;

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start() ;

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("b") ;
                    s2.append("2") ;
                    synchronized (s1) {
                        s1.append("a") ;
                        s2.append("1") ;

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }
}
