package com.satomi.alltest.javaBaseLearning._01thread;

/**
 * 新建
 * 就绪 调用start()
 * 运行 获取cpu资源
 * 阻塞 被挂起或执行输入或输出操作 让出cpu并临时中止
 * 死亡
 *       resume()                     suspend()
 *       sleep()时间到           阻塞   sleep()
 *       获得同步锁              /   \  等待同步锁
 *       notify()/notifyAll()|/     \ wait()/join()
 * 就绪(失去cpu执行权或 yield()) <----> 运行(获取cpu执行权)
 * 运行 ---> 死亡(执行完run()/ 调用stop() / 出现Error或Exception()且没有处理)
 */
public class _2ThreadRunable {

    public static void main(String[] args) {
        //mThread相当于多个线程共享数据
        MThread mThread = new MThread() ;
        Thread t1 = new Thread(mThread);
        /**
         * 调用当前线程的run()
         * 调用public void run() {
         *         if (target != null) {
         *             target.run();
         * private Runnable target;
         */
        t1.start();
        Thread t2 = new Thread(mThread);
        t2.start();
    }
}

/**
 *  实现Runable接口
 *      优先选择使用此方法
 *          java只支持单继承，如果业务需要继承别的类就无法使用
 *          实现的方式更适合处理多个线程有共享数据的情况
 *          Thread也是实现了Runable接口
 * 1 创建一个实现runable接口的类
 * 2 实现runable接口中抽象方法 run()
 * 3 创建实现类的对象
 * 4 将此对象作为参数传递到Thread的构造器中，创建Thread对象
 * 5 通过Thread的对象调用start()
 */
class MThread implements Runnable {
    @Override
    public void run() {
        for(int i = 0 ; i < 100 ; i ++) {
             if (i % 2 == 0) {
                 System.out.println(i);
             }
        }
    }
}
