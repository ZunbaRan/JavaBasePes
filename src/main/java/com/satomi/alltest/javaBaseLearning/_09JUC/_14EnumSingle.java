package com.satomi.alltest.javaBaseLearning._09JUC;

import java.lang.reflect.Constructor;

/**
 * @author nasazumi
 * @description
 *      enum 本身也是一个Class类
 * @date 2020-06-05
 */
public enum _14EnumSingle {

    INSTANCE ;

    public _14EnumSingle getInstance(){
        return INSTANCE ;
    }
}

class Test4{
    public static void main(String[] args) throws Exception{
        _14EnumSingle instance1 = _14EnumSingle.INSTANCE ;

        Constructor<_14EnumSingle> declaredConstructor = _14EnumSingle.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        _14EnumSingle instance2 = declaredConstructor.newInstance();
        /**
         * 编译生成的_14EnumSingle.class文件里 有一个空参构造
         * 实际上并没有
         * javap -p _14EnumSingle.class 反编译
         *
         * jad
         *   └- jad -sjava _14EnumSingle.class
         *   └- _14EnumSingle.java
         *          └- public final class _14EnumSingle extends Enum
         *          └-  _14EnumSingle.class.getDeclaredConstructor(String.class,Integer.class)
         */
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
