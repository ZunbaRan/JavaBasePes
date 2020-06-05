package com.satomi.alltest.javaBaseLearning._03clazz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-01
 */
public class _1DateTimeTest {
    /**
     *
     * System类currentTimeMillis()
     * java.util.Date
     *       |--- java.sql.Date
     * SimpleDateFormat
     *      类内部有一个Calendar对象引用,它用来储存和这个SimpleDateFormat相关的日期信息
     *          sdf.parse(dateStr)
     *              |--- cal.clear()
     *                      cal.set();
     *              Calendar是用来承载字符串转化成日期对象的容器，
     *              calendar对象有个clear后set值的过程，
     *              高并发下，set值的过程，会出现把上次set值给覆盖的情况
     *          sdf.format(date)
     *              |--- calendar.setTime(date)
     *              我们传入的日期对象，会直接用Calendar承载，
     *              高并发下，Calendar承载的对象也会被覆盖
     *      诸如此类的方法参数传入的日期相关String,Date等等,
     *      都是交由Calendar引用来储存的
     *      如果你的SimpleDateFormat是个static的,
     *      那么多个thread 之间就会共享这个SimpleDateFormat,
     *      同时也是共享这个Calendar引用
     *-----------------------------------------------------
     * ps: servlet因是线程不安全的，所以我们使用servlet的原则是不设置成员变量
     * 解决方法
     *      1 将SimpleDateFormat定义成局部变量
     *      2 方法加同步锁synchronized，在同一时刻，只有一个线程可以执行类中的某个方法
     *      3 使用第三方库joda-time，由第三方考虑线程不安全的问题。（可以使用）
     *      4 使用ThreadLocal：每个线程拥有自己的SimpleDateFormat对象。（推荐使用）
     *
     * 如果是JDK8的应用，
     * 可以使用Instant代替Date，
     * LocalDateTime代替Calendar，
     * DateTimeFormatter代替Simpledateformatter，
     * 官方给出的解释：simple beautiful strong immutable thread-safe
     *
     * Calendar
     *------------------------------------------------------
     * jdk8:
     * LocalDate LocalTime LocalDateTime
     *      now()
     *      of()设置指定的年月日是分秒
     *      getMonth()
     *      getDayofWeek()
     *      getDayofMonth()
     *      getMonthValue()
     *      没有偏移量
     *      不可变性
     *      localDate.withDayOfMonth(22) 改为当前月份22号
     *
     * Instant 瞬时--时间戳
     *      Instant.now()
     *      instant.atOffset(ZoneOffset.ofHour(8))
     *
     * java.time.format.DateTimeFormatter
     *      自定义 ofPattern("yyyy-MM-dd hh:mm:ss E")
     */
//    java.lang.String ;
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat() ;
        try {
            Date parse = simpleDateFormat.parse("2020-01-19");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

class DateUtil {

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap
            = new HashMap<String, ThreadLocal<SimpleDateFormat>>();


    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    System.out.println("put new sdf of pattern " + pattern + " to map");

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    /**
     * 使用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,
     * 这样每个线程只会有一个SimpleDateFormat
     * 如果新的线程中没有SimpleDateFormat，才会new一个
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }
}
