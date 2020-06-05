package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-04
 */
public class _04Callable {
    /**
     *   Thread(new Runable)  implements
     *  Thread   <==>   Runable  --> FutureTask
     *                  Callable <-----┘
     *
     *  new Thread((new Runnable(){})).start();
     *  new Thread(new FutureTask<V>()).start();
     *  new Thread(new FutureTask<V>(Callable)).start();
     *
     */

    @Test
    public void test() throws ExecutionException, InterruptedException {
        //怎么启动 Callable
        //new Thread().start();
        MyThread thread = new MyThread() ;
        //适配类
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask,"A").start();
        /**
         * 两个线程只会执行一次
         * 有缓存
         */
        new Thread(futureTask,"B").start();

        /**
         * 这个get方法可能产生阻塞
         * 或者使用异步通信
         */
        Integer o = (Integer)futureTask.get() ;
        System.out.println(o);
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}
