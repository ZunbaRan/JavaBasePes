package com.satomi.alltest.javaBaseLearning._01thread;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-01
 */

/**
 * start()
 * run()
 * static currentThread():
 * getName() //获取当前线程的名字
 * setName()
 * yield() 礼让 释放当前cpu的执行权
 * join()  插队 线程a中调用线程b的join()方法， a就进入阻塞状态，知道b完全执行后，a才结束阻塞状态
 * stop() 强制结束 Deprecated(弃用)
 * sleep(millis 1000) 毫秒
 * isAlive()
 *
 * 线程的优先级
 * public final static int MIN_PRIORITY = 1;
 * public final static int NORM_PRIORITY = 5;
 * public final static int MAX_PRIORITY = 10;
 *
 * setPriority
 * getPriority //获取线程优先级
 *
 */
public class _1ThreadMethodTest {

    public static void main(String[] args) {

        Thread.currentThread().setName("MainThread");

        Mythread h1 = new Mythread();
        //设置优先级
        h1.setName("分线程");
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();
        for (int i = 0 ; i < 100 ; i ++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " "+ i);
            }
//            if (i == 20) {
//                try {
//                    h1.join();
//                } catch (Exception e) {
//
//                }
//            }

        }
    }
}

/**
 * 一 继承Thread类
 * 1 创建一个继承与thread类的子类
 * 2 重写Thread的run（）
 * 3 创建子类的对象
 * 4 通过此对象调用start（）
 */
class Mythread extends Thread {
    @Override
    public void run() {
        for(int i = 0 ; i < 100 ; i ++) {
            if ( i % 2 == 0) {
                System.out.println("子线程" + Thread.currentThread());
            }

            if(i % 20 == 0) {
                /**
                 * 释放当前cpu时间片
                 */
                this.yield(); //this 当前类的对象
            }
        }
    }
}
