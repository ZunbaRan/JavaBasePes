package com.satomi.alltest.javaBaseLearning.clazz;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-02
 */
public class _4Enum {
    public static void main(String[] args) {
        Season spring = Season.SPRING ;
        System.out.println(spring.toString()) ;
        Season summer = Season.SUMMER ;
        System.out.println(summer.toString());

        Season1[] values = Season1.values();
        /**
         * enum.values()
         */
        System.out.println("****************");
        Thread.State[] states = Thread.State.values();
        for(int i = 0 ; i < states.length ; i ++) {
            System.out.println(states[i]);
        }
        /**
         * enum.valueOf("String")
         */
        System.out.println("****************");
        Season1 winter = Season1.valueOf("WINTER");
    }
}

class Season {
    //1 声明属性
    private final String seasonName ;
    private final String seasonDesc ;
    //2 私有化构造器
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName ;
        this.seasonDesc = seasonDesc ;
    }

    //3 提供当前枚举类型的多个对象
    static final Season SPRING = new Season("SPRING", "春");
    static final Season SUMMER = new Season("SUMMER", "夏");
    static final Season AUTUMN = new Season("AUTUMN", "秋");
    static final Season WINTER = new Season("WINTER", "冬");
    //4 其他诉求 获取枚举类对象方法
    public String getSeasonName() {
        return seasonName ;
    }
    public String getSeasonDesc() {
        return seasonDesc ;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

/**
 * enum继承于java.lang.Enum
 * 常用方法
 *  values() 返回枚举类型的对象数组
 *  valueOf(String str) 可以把一个字符串转为对应的枚举类对象
 *  toString() 返回枚举类对象名称
 */
enum Season1 implements Info{
    /**
     * 1 提供当前枚举类型的对象，多个对象用','隔开 末尾用';' 结束
     * 2 声明属性
     * 3 声明私有化构造器
     */
   SPRING("SPRING", "春") {
        @Override
        public void show() {
            System.out.println("春日");
        }
    },
   SUMMER("SUMMER", "夏"){
       @Override
       public void show() {
           System.out.println("宁夏");
       }
   },
   AUTUMN("AUTUMN", "秋") {
       @Override
       public void show() {
           System.out.println("秋月");
       }
   },
   WINTER ("WINTER", "冬") {
       @Override
       public void show() {
           System.out.println("冬季");
       }
   };

    private final String seasonName ;
    private final String seasonDesc ;
    //2 私有化构造器
    private Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName ;
        this.seasonDesc = seasonDesc ;
    }
}

interface Info{
   void show() ;
}


