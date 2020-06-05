package com.satomi.alltest.javaBaseLearning._09JUC;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-05
 */
public class _16Locks {
    /**
     *  可重入锁
     *      └- 拿到外面的锁，就能拿到里面的锁
     *  Lock 上几把锁，就解几把锁
     *  Synchronized 相当于上了一把锁
     *
     *  死锁
     *    └- synchorized(A) {
     *          synchorized(B)
     *       }
     *    └- synchorized(B) {
     *          synchorized(A)
     *    }
     *   └- 解决方法
     *          └- jps -l 定位进程号
     *          └- jstack 进程号
     */
}
