package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author nasazumi
 * @description
 *  池化技术
 *      └- 事先准备好一些资源，有人要用直接来拿，用完还给我
 *      └- 好处
 *          └- 降低资源消耗速度
 *          └- 提高相应的速度
 *          └- 方便管理
 *
 *      └- 不使用Executors
 *           |  └- 其实是一个工具类 三大方法
 *           |  └- Executors.newFixedThreadPool(5) ; 固定的线程池大小
 *           |  └- Executors.newSingleThreadExecutor() ; 单个线程
 *           |  └- Executors.newCachedThreadPool() ;  可伸缩
 *           |                  └- maximumPoolSize Integer.Max
 *           └- threadPool.execute(Runable())
 *           └- finally threadPool.shutDown()
 *           └- 七大参数
 *                  └- 本质 ThreadPoolExecutor()
 *                  |       |   └- int corePoolSize       核心线程池大小
 *                  |       |   └- int maximumPoolSize    最大核心线程池大小
 *                  |       |           └- 如何定义最大线程
 *                  |       |                   └- cpu密集型 几核就是几 保持cpu效率最高
 *                  |       |                   |       └- 获取cpu核数 Runtime.getRuntime().availableProcessors()
 *                  |       |                   └- IO 密集型 判断程序中十分耗io的线程 大于这个数量
 *                  |       |                           └- 15个大型任务 io十分占用资源
 *                  |       |   └- long keepAliveTime     超时了没有人调用就会释放
 *                  |       |   └- TimeUnit unit          超时单位
 *                  |       |   └- BlockingQueue<Runable> workQueue  阻塞队列
 *                  |       |   └- ThreadFacotry threadFactory       线程工厂 一般不用
 *                  |       |   └- RejectedExecutionHandler handler  拒绝策略
 *                  |       └- 银行窗口 -> 核心线程池
 *                  |       └- 银行窗口全部开放 -> 最大线程池
 *                  |       └- 候客区 -> 阻塞队列
 *                  |       └- 银行窗口阻塞队列全满了之后进来的人 -> 拒绝策略
 *                  └- CallerRunsPolicy     哪里来的回哪里
 *                  └- AbortPolicy          不处理 抛出异常
 *                  └- DiscardPolicy        直接丢掉任务 不会抛出异常
 *                  └- DiscardOldestPolicy  尝试和最早的线程竞争 不抛异常
 *
 * @date 2020-06-05
 */
public class _07pool {

    @Test
    public void test1(){
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        ) ;

        try{
            for (int i = 0; i < 7; i++) {
                final int temp = i ;
                threadPool.execute(() -> {
                    System.out.println(temp);
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
