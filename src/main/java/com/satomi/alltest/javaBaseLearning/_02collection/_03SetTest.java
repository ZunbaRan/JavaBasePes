package com.satomi.alltest.javaBaseLearning._02collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author nasazumi
 * @description
 * Collection接口
 *      └- Set 常见实现类  没有定义额外的新的方法，使用的都是Collection的方法
 *          └- HashSet
 *          |    └- LinkedHashSet : 作为hashSet的子类，遍历内部子类是可以按照添加的顺序遍历
 *          └- TreeSet: 数据结构红黑树
 *               └- 可以按照添加对象的指定属性进行排序
 * @date 2020-06-02
 */
public class _03SetTest {
    /**
     *  Set 存储无序，不可重复的数据
     *      1. 无序性，不等于随机性
     *      2. 不可重复
     */

    @Test
    public void test01() {
        Set set = new HashSet() ;
        set.add(321);
        set.add(123) ;
        set.add("aa") ;
        set.add("cc") ;

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     *  向TreeSet添加的数据，要求是相同类的对象
     *      └- 自然排序
     *      |     └- 添加对象实现compareTo接口(不实现无法添加对象)
     *      |     |  判断两个对象是否相同的标准就调用compareTo方法，不再是equals方法
     *      |     └- 返回为0则认为相同
     *      └- 定制排序
     *            └- 实现Comparator接口
     *            └- new TreeSet(comparator)
     */
    @Test
    public void test02() {
        TreeSet set = new TreeSet() ;
        set.add(321);
        set.add(123) ;
        set.add(-12) ;
        set.add(8) ;
        set.add(45) ;
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            //从小到大排序
            System.out.println(iterator.next());
        }
    }
}
