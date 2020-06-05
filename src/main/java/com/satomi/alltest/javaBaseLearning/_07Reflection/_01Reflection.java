package com.satomi.alltest.javaBaseLearning._07Reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author nasazumi
 * @description  ┌└ ┐┘
 *
 *    ┌- 运行时判断任意一个对象所属的类
 *    |  运行时构造任意一个类的对象
 *    |  运行时判断任意一个类所具有的成员变量和方法
 *    |  运行时获取泛型信息
 *    |  运行时调用任意一个对象的成员变量和方法
 *    |  运行时处理注解
 *    └- 生成动态代理
 *
 *      └- 泛着机制与面向对象中的封装性
 *          └- 不矛盾
 *      └- 对 java.lang.Class的理解
 *          └- 类的加载过程
 *               └- 程序经过javac.exe 命令以后，会生成一个或多个字节码文件(.class）
 *               |  接着使用java.exe命令对某个字节码文件进行解释
 *               |  相当于把字节码文件加载到内存中，称为类的加载
 *               |  加载到内存中的类就称为运行时类
 *               |  此运行时类就作为Class的一个实例
 *               |  Class的实例就对应一个运行时类
 *               |  加载到内存中的运行时类，会缓存一定的时间，
 *               └- 在此时间之内我们可以通过不同的方式来获取此运行时类
 *
 * @date 2020-06-03
 */
public class _01Reflection {

    @Test
    public void test() throws Exception {
        //反射
        Class clazz = Person.class ;
        Constructor constructor = clazz.getConstructor(String.class, Integer.class);
        Object obj = constructor.newInstance("Tom", 22) ;
        System.out.println(obj.toString());
        /**
         * 通过反射调用属性 方法
         */
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(obj) ;
        /**
         * 通过反射 可以调用Person类的私有结构 比如私有构造器
         *      newInstance
         *      java bean 中要求提供一个public的空参构造器
         *         └- 通过反射创建运行时对象
         *         └- 子类继承调用super() 默认调用父类空参构造器
         */
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person jerry = (Person)cons1.newInstance("jerry");
        System.out.println(jerry);
        /**
         * 修改私有的属性
         */
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(obj, "lana") ;
        System.out.println(obj.toString());

    }

    @Test
    public void test1() throws Exception {
        /**
         * 获取Class的实例的方式
         *      └- 1 调用运行时类的属性 .class
         *      └- 2 通过运行时累的对象，调用getClass()
         *      └- 3 调用Class的静态方法: forName(String path)
         *      └- 4 使用类的加载器
         *              └- Bootstap Classloader 负责java核心库 引导类加载器,无法直接获取
         *              └- Extension Classloader 负责jre/lib/ext
         *              └- System Classloader
         *                   └- App Classloader
         *
         */
        // 1
        Class clazz1 = Person.class ;
        System.out.println(clazz1);
        // 2
        Person p1 = new Person() ;
        Class clazz2 = p1.getClass() ;
        System.out.println(clazz2);
        // 3
        Class clazz3 = Class.forName("com.satomi.alltest.javaBaseLearning._07Reflection.Person") ;

        System.out.println("clazz1 == clazz2" + (clazz1 == clazz2));
        System.out.println("clazz1 == clazz3" + (clazz1 == clazz3));
        //4
        ClassLoader classLoader = _01Reflection.class.getClassLoader() ;
        Class clazz4 = classLoader.loadClass("com.satomi.alltest.javaBaseLearning._07Reflection.Person") ;
        System.out.println("clazz1 == clazz4" + (clazz1 == clazz4));
    }
}

class Person{
    private String name ;
    private Integer age ;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name ;
        this.age = 0 ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    void show(){
        System.out.println("name" + this.name + "age" + this.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
