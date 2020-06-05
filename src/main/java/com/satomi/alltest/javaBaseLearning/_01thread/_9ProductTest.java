package com.satomi.alltest.javaBaseLearning._01thread;

import lombok.SneakyThrows;

/**
 * @author nasazumi
 * 线程通信的应用
 * 生产者 Producer
 * 店员 Clerk
 * 消费者 Customer
 * 店员一次只能持有固定数量的产品 20
 *
 * 多线程
 *     生产者线程 消费者线程
 * 持有共享数据
 *      店员 或者产品
 * 线程安全问题
 *      同步机制 三种方法
 * 涉及到线程通信
 */

class Clerk {
    private int productCount = 0 ;
    //生产产品
    public synchronized void produceProduct() throws InterruptedException {
        if(productCount < 20) {
            productCount ++ ;
            System.out.println(Thread.currentThread().getName() + "开始生产第" + productCount + "个产品");
            notify();
        } else {
            wait();
        }
    }
    //消费产品
    public synchronized void consumeProduct() throws InterruptedException {
        if(productCount >= 20) {
            System.out.println(Thread.currentThread().getName() + "开始消费第" + productCount + "个产品");
            productCount -- ;
            notify();
        } else {
            wait();
        }

    }
}

class Producer extends Thread{
    private Clerk clerk ;

    public Producer(Clerk clerk) {
        this.clerk = clerk ;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(getName() + "生产产品");
        while (true) {
            Thread.sleep(100);
            clerk.produceProduct();
        }
    }
}

class Customer extends Thread {
    private Clerk clerk ;

    public Customer(Clerk clerk) {
        this.clerk = clerk ;
    }
    @SneakyThrows
    @Override
    public void run() {
        System.out.println(getName() + "消费产品");
        while (true) {
            Thread.sleep(50);
            clerk.consumeProduct();
        }
    }

}

public class _9ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk() ;

        Producer p1 = new Producer(clerk) ;
        p1.setName("生产者");

        Customer c1 = new Customer(clerk) ;
        c1.setName("消费者1 ");

        Customer c2 = new Customer(clerk) ;
        c2.setName("消费者2 ");

        p1.start();
        c1.start();
        c2.start();
    }
}
