package com.satomi.alltest.javaBaseLearning._03clazz;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-01
 */

public class _2StringBuffer {

    /**
     * new String("abc") //new char[]{a,b,c}
     *
     * StringBuffer
     *   public StringBuffer(){} //new char[16];
     *   sb1.append('a') //value[0] = 'a'
     *
     *   StringBuffer('abc') //
     *      public StringBuffer(String str) {
     *          super(str.length() + 16)
     *          append(str)
     *      }
     *      length() -> public synchronized int length(){return count} 实际长度
     *      value.length 数组长度
     *
     *   StringBuffer 如果要添加的数据底层数组存不下类，就需要扩容
     *      ensureCapacityInternal(count + len)
     *      默认情况下 新建数组为原来的2倍 + 2，复制原来数组的内容到新数组
     *          1特殊情况如 字符串过长，扩容后仍存不下，就把字符串直接作为数组
     *          2长度超出限制，变为负数 overflow
     *
     */
java.lang.StringBuffer bu = new java.lang.StringBuffer();
}
