package com.satomi.alltest.javaBaseLearning.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author nasazumi
 * Executors ExecutorService
 */
public class _11ThreadPool {
    public static void main(String[] args) {
        /**
         * 1 提供线程池
         * 2 用线程池对象执行指定线程的操作
         *      好处
         *          提高响应速度(减少了创建新线程的时间)
         *          降低资源消耗(重复利用线程池中的线程)
         *          便于线程管理
         *              corePoolSize    核心线程数
         *              maximumPoolSize 最大线程数
         *              keepAliveTime   没有任务时最多保存多久
         */
        ExecutorService service = Executors.newFixedThreadPool(10) ;
        /**
         * service.execute(); 没有返回值
         * submit()适合用于Callable
         */
        service.execute(new NumberThread());
        //线程池关闭
        service.shutdown();
    }
}

class NumberThread implements Runnable{
    @Override
    public void run() {
            int sum = 0 ;
            for (int i = 1 ; i <= 100 ; i ++) {
                if(i % 2 == 0) {
                    System.out.println(i);
                    sum += i ;
                }
        }
    }
}
