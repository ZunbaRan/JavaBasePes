package com.satomi.alltest.javaBaseLearning._09JUC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author nasazumi
 * @description
 *      Future 对未来发生的事件进行建模
 *      异步调用 CompletableFuture
 * @date 2020-06-05
 */
public class _11Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 发起一个请求
        CompletableFuture<Void> completableFuture =
                CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "runAsync -> Void");
                }) ;

        System.out.println("111");
        //获取执行结果
        completableFuture.get() ;
    }

    static void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + "supplyAsync -> Void");
                    return 1024 ;
                }) ;

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t -> " + t);  // 正常结果
            System.out.println("u -> " + u);  // 错误信息
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233; //可以获得错误的返回结果
        }).get());

        System.out.println("111");
    }
}
