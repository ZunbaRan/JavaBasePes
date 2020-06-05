package com.satomi.alltest.javaBaseLearning._09JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-05
 */
public class _15CAS {

    AtomicInteger atomicInteger = new AtomicInteger() ;

    // CAS compareAndSet 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020) ;

        /**
         *  期望 更新
         *  public final boolean compareAndSet(int expect, int update)
         *  如果期望的值达到了，就更新，否则不更新
         *  CAS 是cpu的并发原因
         *      └- Unsafe 类是java的内存，通过这个类操作内存
         */
        atomicInteger.compareAndSet(2020, 2021) ;
        //atomicInteger.getAndIncrement() ;
        /**
         * CAS 比较并交换
         * └- 比较当前工作内存中的值和主内存中的值，如果这个值是期望的，那么执行操作
         *    如果不是就一直循环
         *    自带原子性 不用切换线程状态
         * └- 缺点
         *      └- 循环耗时
         *      └- 一次性只能保证一个共享变量的原子性
         *      └- ABA
         *
         * public final int getAndIncrement() {
         *         return unsafe.getAndAddInt(this, valueOffset, 1);
         *     }
         * └- public final int getAndAddInt(Object var1, long var2, int var4) {
         *         int var5;
         *         自旋锁 do while
         *         do {
         *             var5 = this.getIntVolatile(var1, var2);
         *              └- 获取内存地址偏移量
         *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
         *                                              └- 如果 var1var2 的内存地址偏移量还是var5 ， 就var5 + 1
         *         return var5;
         *     }
         *
         *  └- ABA问题 (狸猫换太子)
         */
        System.out.println(atomicInteger.get());

        boolean b = atomicInteger.compareAndSet(2020, 2021);
        System.out.println(b);
    }
}

class Aba {
    public static void main(String[] args) {
        /**
         * Integer使用了对象缓存机制 默认-128 - 127 推荐使用静态工厂方法
         * valueOf获取而不是 new，valueOf使用缓存，而new一定会创建新的对象分配新的空间
         *
         * 所有包装类对象之间的值比较，全部使用equals方法
         *      对于Integer var = ？ 在-128 - 127 之间的赋值，Integer对象是在IntegerCache.chche产生
         *      会复用已有对象，这个区间的Integer值可以直接使用 ==
         *      这个区间以外的所有数据，都会在堆上产生
         * 2020 会产生新的对象，导致两次引用的不是一个对象
         */
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1) ;
        /**
         * 跟乐观锁操作一样 ，带版本号
         */
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp() ;
            System.out.println("a1 ->" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), //获取版本号
                    atomicStampedReference.getStamp() + 1));
            System.out.println("a2 ->" + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), //获取版本号
                    atomicStampedReference.getStamp() + 1));
            System.out.println("a3 ->" + atomicStampedReference.getStamp());
        },"a").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp() ;
            System.out.println("b1 ->" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 6,
                    atomicStampedReference.getStamp(), //获取版本号
                    atomicStampedReference.getStamp() + 1));
            System.out.println("b2 ->" + atomicStampedReference.getStamp());
        },"b").start();

}

    static void testAba() {
        AtomicInteger atomicInteger = new AtomicInteger(2020) ;

        /**
         * 平时写的sql 乐观锁
         */
        /**
         * 捣乱的线程
         */
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2021, 2020));
        System.out.println(atomicInteger.get());
        /**
         * 期望的线程
         */
        System.out.println(atomicInteger.compareAndSet(2020, 6666));
        System.out.println(atomicInteger.get());
    }
}
