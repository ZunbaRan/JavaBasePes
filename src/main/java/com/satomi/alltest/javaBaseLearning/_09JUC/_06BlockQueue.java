package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-05
 */
public class _06BlockQueue {
    /**
     *  Iterable
     *       └- Collention
     *           └- Set
     *           └- List
     *           └- Queue
     *              └- Deque 双端队列
     *              └- AbstractQueue 非阻塞队列
     *              └- BlockingQueue 阻塞队列
     *                  └- ArrayBlockingQueue
     *                  └- LinkedBlockingQueue
     *  队列 不得不阻塞
     *      └- 写入 如果队列满了，就必须阻塞等待
     *      └- 读取 如果队列为空，必须阻塞等待生产
     *  什么情况下会使用阻塞队列
     *
     *  BlockingQueue
     *      └-4组api
     *          └- 1 抛出异常
     *                  └- add
     *                  └- remove
     *                  └- element
     *          └- 2 不会抛出异常
     *                  └- offer
     *                  └- poll
     *                  └- peek
     *          └- 3 阻塞等待
     *                  └- put
     *                  └- take
     *          └- 4 超时等待
     *                  └- offer(,,)
     *                  └- poll(,,)
     *  SynchornizedQueue
     *          └- 同步队列
     *          └- 进去一个元素必须在取出之后，才能再次添加元素
     *          └- put take
     *
     */

    @Test
    public void test01(){
        //队列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        /**
         * 抛出异常
         * java.lang.IllegalStateException: Queue full
         */

        //System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        /**
         * java.util.NoSuchElementException
         */
        //System.out.println(blockingQueue.remove());

        /**
         * 查看队首
         */
        System.out.println(blockingQueue.element());
    }

    @Test
    public void test02() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        /**
         * true
         * true
         * true
         * false
         */
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //System.out.println(blockingQueue.offer("d"));
        /**
         * 超时等待
         * 超过两秒就退出
         */
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));

        /**
         *  a
         *  b
         *  c
         *  null
         */
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.poll());
        /**
         * 超过两秒就退出
         */
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));

        /**
         * 查看队首
         */
        System.out.println(blockingQueue.peek());
    }

    @Test
    public void test03() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        //一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        /**
         * 队列没有位置了会一直等待
         */
        //blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        /**
         * 没有这个元素会一直阻塞
         */
        //System.out.println(blockingQueue.take());
    }
}
