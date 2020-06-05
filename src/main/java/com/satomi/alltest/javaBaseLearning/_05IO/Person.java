package com.satomi.alltest.javaBaseLearning._05IO;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-03
 */

import java.io.Serializable;

/**
 *  序列化类
 */
class Person implements Serializable {
    //序列版本号
    /**
     * serialVersionUID
     *     | java可以运行时环境可以根据类内部细节自动生成
     *     | 若类的实例做了修改 serialVersionUID 可能发生变化
     *     |
     *     └- 反序列化时 JVM会把传来的字节流的serialVersionUID进行比较
     *     |  如果相同认为是一直，可以进行反序列化
     *     └- 否则版本不一致版本不一致，会出现InvalidCastException
     */
    public static final long serialVersionUID = 485971646L ;

    private String name ;
    private Integer age ;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
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
}