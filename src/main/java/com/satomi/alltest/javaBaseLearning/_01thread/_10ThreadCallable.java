package com.satomi.alltest.javaBaseLearning._01thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author nasazumi
 * @description Callable
 */

/**
 * FutrueTask是Futrue唯一的实现类
 * FutrueTask同时实现了Runable Futrue接口
 * 即可以作为Runable被线程执行，又可以作为futrue获得返回值
 *
 *  如何理解Callable比Runable接口创建多线程方式强大
 *      call()可以有返回值
 *      call()可以抛出异常被外面的操作捕获
 *      callable支持范型
 */
public class _10ThreadCallable {
    public static void main(String[] args) {
        NumThread numThread = new NumThread() ;

        FutureTask futureTask = new FutureTask(numThread);
        //启动线程
        new Thread(futureTask).start();
        try {
            // futureTask.get() 返回值即为FutureTask的构造器（numThread）的返回值
            Object sum = futureTask.get();
            System.out.println("sum" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 1 创建实现Callable的实现类
 * 2 实现Callable方法
 * 3 创建Callable实现类的对象
 * 4 将此Callable的对象传递到FutureTask构造器中 创建FutureTask对象
 * 5 将FutureTask对象作为参数传递到Thread类的构造器中，创建Thread对象并调用start()
 */
class NumThread implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0 ;
        for (int i = 1 ; i <= 100 ; i ++) {
            if(i % 2 == 0) {
                System.out.println(i);
                sum += i ;
            }
        }
        //自动装箱
        return sum ;
    }
}