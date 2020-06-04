package com.satomi.alltest.javaBaseLearning.collection;

/**
 * @author nasazumi
 * @description
 *      JDK API中List接口常见实现类
 *                 |--- 动态数组 用于替换原有的数组
 *                 |--- ArrayList : 作为List接口的主要实现类,底层使用Object[] elementData
 *                 |--- LinkedList : 对于频繁的插入删除操作,效率比ArrayList高; 双向链表实现
 *                 |--- Vector : 远古类；线程安全效率低, 底层使用Object[] elementData
 * @date 2020-06-02
 */
public class _02ListTest {

    /**
     * ArrayList 源码  ┌└ ┐┘
     *     jdk 1.7
     *     --|  在构造器中就创建数组  --> 类似于饿汉式
     *       └- private transient Object[] elementData;
     *       └- new ArrayList() {
     *       |      创建一个长度为10的Object数组
     *       |      this(initialCapaty: 10)
     *       |  }
     *       └-  public boolean add(E e){
     *       |     ensureCapacityInternal(minCapacity) ;
     *       |          └- 相减操作
     *       |     elementData[size ++] = e ;
     *       |  }
     *       └-  grow(minCapacity) {
     *              默认扩容为1.5倍
     *              newCapacity = oldCapacity + (oldCapacity >> 1)
     *              Arrays.copyOf(elementData, newCapacity)
     *          }
     *     JDK 1.8
     *     --|     不会在创建对象的时候就实例化数组 --> 类似于懒汉式
     *       └- private static final Object[]
     *       |      EDFAULTCAPACITY_EMPTY_ELEMENTDATA = {}
     *       └- public boolean add(E e){
     *              第一次在add的时候才创建数组,并将数据添加到elementData
     *              ensureCapacityInternal(minCapacity) ;
     *                  └- Math.max(DEFAULT_CAPACITY, minCapacity)
     */

    /**
     * LinkedList 源码
     *      |  transient int size = 0
     *      |  transient Node<E> first
     *      └- transient Node<E> last
     *      |       |--- private static class Node<E> {
     *      |       |       E item ;
     *      |       |       Node<E> next ;
     *      |       |       Node<E> prev ;
     *      |       └-   }
     *      └- public boolean add(E e) {
     *          linkLast(e);
     *              |--- void linkLast(E e) {
     *              |       final Node<E> l = last;
     *              |       // Node构造器(前一个, 本身数据, 下一个)
     *              |       final Node<E> newNode = new Node<>(l, e, null) ;
     *              |       // 把新节点设置为最后一个
     *              |       last = newNode ;
     *              |       if(l == null)
     *              |           // 第一次add l为空，新节点为第一个节点,即是first又是last
     *              |           first = newNode ;
     *              |       else //否则Last的next指向新节点
     *              |           l.next = newNode ;
     *              |       size ++;
     *              └-----  modCount ++ ;
     *      }
     */

    /**
     *  Vector(){this(10)}
     *      └- public synchronized boolean add(E e)
     */
    // LinkedList
}
