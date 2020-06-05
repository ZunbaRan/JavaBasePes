package com.satomi.alltest.javaBaseLearning._02collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author nasazumi
 * @description
 *  1 集合 数组都是对多个数据进行存粗操作的结构，简称java容器
 *    说明 此时的存储主要是指内存层面的存储，不涉及到持久化存储(.txt,.jpg,数据库)
 *  2.1 数组在存储多个数据方面的特点
 *      > 一旦初始化以后，长度就确定了
 *      > 数组一旦定义好，其元素的类型就去订了，只能操作指定类型的数据
 *          String[] arr ; int[] arr1; Object[] arr2 ;
 *  2.2 缺点
 *      > 数组提供的方法非常有限，对于添加、删除、插入非常不方便，效率不高
 *      > 存储数据特点：有序，可重复，对于无序、不可重复的需求无法满足
 *  集合框架
 *      Collection coll = new ArrayList() ;
 *      add()
 *      remove()
 *      removeAll()
 *      contains()
 *      containsAll() 差集
 *      retainAll() 交集
 *      toArray()
 *      iterator() 返回Iterator接口的实例
 *          迭代器 --> 设计模式
 *                      访问容器对象的各个元素，而又不暴露该对象内部的细节
 *             |--- hasNext()
 *             |--- next()
 *             |--- defult remove()
 *          迭代器执行原理  不存放数据，只用来遍历
 *              Iterator iterator = coll.iterator();
 *              iterator.next --> 数组头
 *              指针下移 --> 判断 hasNext()
 *          每次调用iterator()方法都会新建一个对象
 *          remove()
 *              |--- 内部定义了remove() 此方法不同于集合直接调用remove()
 *   foreach 增强for循环, 用于遍历集合和数组
 *
 * @date 2020-06-02
 */
public class _01CollectionTest {

}

class IteratorTest {

    public void test(String[] args) {
        Collection coll = new ArrayList() ;
        Iterator iterator = coll.iterator();
    }
}
