package com.satomi.alltest.javaBaseLearning._09JUC;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nasazumi
 * @description  ┌└ ┐┘  ↑ ↓ ← → ↖ ↗ ↙ ↘ ↔ ↕
 *      Volatile Java虚拟机提供的轻量级同步机制
 *
 *      JMM java内存模型
 *              └- 实际不存在 概念 约定
 *              └- 同步约定
 *                  └- 线程解锁前，必须把共享变量立刻刷回主存
 *                  └- 线程加锁前，必须读取主存重的最新值到工作内存中
 *                  └- 加锁和解锁是同一把锁
 * 缓存行 MESI 内存屏障
 *
 *      | └-主存
 *      |    └- 变量
 *      | └- 线程A
 *      |    └- 工作内存
 *      |    └- 执行引擎
 *      | └- 线程B
 *      |    └- 工作内存
 *      |    └- 执行引擎
 *      └- 操作
 *         | └- 内存交互操作有8种，虚拟机实现保证每一个操作都是原子的
 *         |    必须成对出现
 *         | └- read
 *         |    load
 *         | └- use
 *         |    assign
 *         | └- write
 *         |    store
 *         └-└- lock
 *              unlock
 *                ┌-----┐
 *          write | 主存 |
 *     ┌---┐----->| obj |-----> ┌---┐
 *     |   |      └-----┘  read |   |
 *     └---┘                    └---┘
 *   store↑    ┌-----------┐     |
 *        |    |  Thread   |     | load
 *        |    |┌---------┐|     |
 *        └--- || memory  || <---┘
 *             |└-↑-----|-┘| ←use
 *     assign→ |┌-|-----↓-┐|
 *             || engine  ||
 *             |└---------┘|
 *             └-----------┘
 *
 * @date 2020-06-05
 */
public class _12Volatile {

    /**
     *  volatile
     *      └- 不保证原子性
     *          └- CAS
     *              └- Unsafe类
     *              └- 这些类的底层都直接和操作系统挂钩，在内存中修改值
     *          └- 原子类
     *              └- AtomicBoolean
     *              └- AtomicInteger
     *              └- AtomicLong
     *      └- 防止指令重排
     *              └- 计算机并不是按照写的代码执行的
     *              └- 源代码 -> 编译器优化重排 -> 指令并行可能重排 -> 内存系统也会重排 -> 执行
     *              └- 处理器在进行指令重排的时候 考虑 数据之间的依赖性
     *           └- 内存屏障 cpu指令
     *                  └- 1 保证特定的操作的执行顺序
     *                  └- 2 可以保证某些变量的内存可见性(利用这些保证volatile内存可见性)
     *                      普通读
     *                        |
     *                      普通写
     *                        |
     *        内存屏障：禁止上面的指令和下面的指令顺序交换
     *                    volatile写
     *        内存屏障：禁止上面的指令和下面的指令顺序交换
     *
     *
     */

    private volatile static int num = 0 ;

    public static void main(String[] args) {

        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        num = 1 ;
        System.out.println(num);
    }
}

class Test02{

    //private volatile static int num = 0 ;
    private volatile static AtomicInteger num = new AtomicInteger() ;

    //public synchronized static void add() {
    public static void add() {
        //不是一个原子性操作
        /**
         * getstatic
         * iadd
         * putstatic
         */
        //num ++ ;
        num.getAndIncrement() ; // AtomicInteger + 1  CAS
    }

    public static void main(String[] args) {
        test();
    }
    static public void test() {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add() ;
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {  //main gc
            Thread.yield();
        }
        System.out.println(num);
    }
}
