package com.satomi.alltest.javaBaseLearning._09JUC._03unsafe;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-04
 */
public class ListTest {
    public static void main(String[] args) {
        //List<String> list = Arrays.asList("1", "2", "3") ;
        //list.forEach(System.out:: println);

        /**
         *  java.util.ConcurrentModificationException 并发修改异常
         *  并发下 ArrayList是不安全的
         *      └- 1. new Vector
         *      |       └- Synchornized 修饰的方法效率低
         *      └- 2. Collections.synchronizedCollection()
         *      └- 3. JUC
         *              └- ConcurrentHashMap
         *              └- ConcurrentHashMapKeySetView
         *              └- ConcurrentLinkedDeque
         *              └- ConcurrentLinkedQueue
         *              └- CopyOnWriteArrayList<E>
         *              |   └- 写入时复制 计算机程序设计领域的一种优化策略
         *              |   └- 在写入的时候，复制一个数组，写入完后再插入进去，避免覆盖造成数据问题
         *              |   └- private transient volatile Object[] array
         *              |   |   └- add(E e)
         *              |   |       └- ReentrantLock lock = this.lock
         *              |   |       |  Object[] newElements = Array.copyOf(elements, len + 1)
         *              |   |       └- setArray(newElements)
         *              |   └- 比Vector的优点
         *              └- CopyOnWriteArraySet<E>
         *              └- CountDownLatch
         *              └- Semaphore
         *              └- CyclicBarrier
         *
         *  HashMap 工作中不用HashMap
         *  默认等价于 new HashMap<>(o.75f,16)
         *                   |      └- DEFAULT_LOAD_FACTOR 0.75
         *                   |      └- DEFAULT_INITIAL_CAPACITY 1<<4
         *                   └- ConcurrentHashMap
         */
        //List<String> list = new ArrayList<>() ;

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5)) ;
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 5)) ;
                System.out.println(1);
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
