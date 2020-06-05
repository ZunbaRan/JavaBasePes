package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-05
 */
public class _10ForkJoin<T> extends RecursiveTask<Long> {
    /**
     *  ForkJoin
     *      └- 并行执行任务 提高效率
     *      └- 工作窃取 里面维护的都是双端队列
     *      └- 操作
     *          └- ForkJoinPool 通过它来执行
     *          └- forkjoinPool.execute(ForkJoinTask task) 计算任务
     *                               继承   └- RecursiveAction 递归事件
     *                                                  └- 没有返回值
     *                                      └- RecursiveTack   递归任务
     *                                                  └- 有返回值
     */
    private Long start ;
    private Long end ;
    //临界值
    private Long temp = 10000L ;

    public _10ForkJoin(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if((end - start) > temp) {
            /**
             * 分支合并计算
             */
            Long sum = 0L ;
            for (Long i = start; i <= end; i++) {
                sum += i ;
            }
            System.out.println(sum);
            return sum ;
        }else { // forkjoin
            long middle = (start + end) / 2 ;
            _10ForkJoin<Long> forkJoin1 = new _10ForkJoin(start, middle);
            /**
             * 拆分任务， 把任务压入线程队列
             */
            forkJoin1.fork() ;
            _10ForkJoin<Long> forkJoin2 = new _10ForkJoin(middle + 1, middle);
            forkJoin2.fork() ;
            return forkJoin1.join() + forkJoin2.join() ;
        }
    }
}

class Test5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test01() ;
//        test02() ;
        test03() ;
    }


    public static void test01() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i < 10_0000_0000 ; i++) {
            sum += i ;
        }
        long end = System.currentTimeMillis() ;
        System.out.println("sum += " + "时间" + (end - start));
    }

    /**
     *  使用forkJoin
     */
    public static void test02() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool() ;
        _10ForkJoin<Long> forkJoin = new _10ForkJoin(0L, 10_0000_0000L);
        //forkJoinPool.execute(forkJoin); 执行任务 没有返回值
        /**
         * 提交任务，有返回结果
         */
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoin);
        Long aLong = submit.get();

        long end = System.currentTimeMillis() ;
        System.out.println("sum += " + "时间" + (end - start));
    }

    /**
     *  使用并行流
     */
    public static void test03() {
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis() ;
        System.out.println("sum += " + "时间" + (end - start));
    }

}