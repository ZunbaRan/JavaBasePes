package com.satomi.alltest.javaBaseLearning._08JVM;

/**
 * @author nasazumi
 * @description  ┌└ ┐┘
 *
 * 1. JVM的位置
 *   JVM就是一个软件，java程序跑在JVM上
 *   jre --> java运行环境 包含JDK
 * 2. JVM的体系结构
 *   java --> ClassFile --> ClassLoader
 *   运行时数据区 Runtime Data Aera
 *      └- 方法区 Method
 *      |  java栈 Stack
 *      |  本地方法栈 Native Method Stack
 *      |  堆 Heap
 *      └- 程序计数器
 *   执行引擎
 *      └- 解释器
 *      └- 垃圾回收器
 * 3. 类加载器
 *      └- Car.class -> ClassLoader -> Car class -> Car instances
 *                                          └- getClass <-┘ 不同的Car实例getClass的类对象是同一个
 *      ClassLoader
 *          └- 1 虚拟机自带的加载器
 *          |  2 启动类(根)加载器  BootStrapClassLoader jre/lib/rt.jar
 *          |  3 扩展类加载器      ExtClassLoader       jre/lib/ext
 *          └- 4 应用程序加载器    AppClassLoader
 * 4. 双亲委派机制
 *      └- 自己建立 java.lang.String --> 运行报错没有main方法
 *      └- 类加载器收到类加载请求
 *      |  将这个请求向上委托给父类加载器区完成，一直向上委托直到启动类记载器
 *      |  App -> Ext -> Boot(最终执行)
 *      └- App <- Ext <- Boot没有这个类
 * 5. 沙箱安全机制
 * 6. Native
 * 7. PC寄存器
 *      └- 程序计数器
 *            └- 每个线程都有一个程序计数器，是线程私有的
 * 8. 方法区
 *      └-  所有线程共享 所有字段和方法字节码 构造函数接口代码也在此
 *          静态变量 常量 类信息 运行时的常量池也存在方法区，实例变量在堆内存中
 *          static final, Class模板
 *      └- 创建对象内存分析
 *              └- 栈
 *                  └- main 方法
 *                  └- new Object：引用变量名
 *              └- 堆                    |
 *                  └- Object实际存储 <---┘
 *                      └- method() ----┐
 *                      └- static       |
 *              └- 方法区                |
 *                  └- 类信息            |
 *                      └- Applacation  |
 *                      └- main()       |
 *                      └- 常量池        |
 *                  └- 类对象模板 --------|--┐
 *                      └- method() <---┘  |
 *              └- 静态方法区                |
 *                  └- static <------------┘
 *
 * 9. 栈
 *     └- 数据结构  基本类型 + 对象引用 + 实例的方法
 *          └- 栈帧
 *              └- 方法索引
 *              └- 输入输出参数
 *              └- 本地变量
 *              └- Class File引用 -> 引用堆内存 -> static引用静态方法区
 *              └- 父帧
 *              └- 子帧
 *     └- 不存在垃圾回收问题，一旦线程结束 栈就销毁
 * 10.三种JVM
 *      └- sun Java HotSpot
 *      └- Oracle JRockit
 *      └- IBM J9 VM
 * 11.堆
 *     └- Heap 一个JVM只有一个堆内存，堆内存的大小是可以调节的
 * 12.新生区 老年区
 *      └- 新生区
 *      |    └- Eden
 *      |   ┌> Survivor 0 -┐ from
 *      └-  └- Survivor 1 <┘ to
 *      └- 老年区
 *          └- 养老区
 *          └- 永久存储区
 *              └- jdk 8 元空间
 *              └- 方法区
 * 13.永久区
 * 14.堆内存调优    设置初始化内存分配大小 1/64  设置最大分配内存 默认1/4                   打印GC垃圾回收信息
 *      └- VM options：    -Xmslm                -Xmx8m            -XX：+HeapDump +PrintGCDetails
 *      └- JPofiler
 * 15.GC
 *      └- 常用算法
 *      |   └- 1 标记清除法
 *      |   |       └- 扫描对象，对活着的对象进行标记
 *      |   |       └- 清除：对没有标记的对象进行清除
 *      |   |       └- 优点：不需要额外空间
 *      |   |          缺点：两次扫描 严重浪费时间，会产生内存碎片
 *      |   └-  2 标记压缩
 *      |   |       └- 标记清除的再优化
 *      |   |               |  再次扫描，对活着的对象进行整理
 *      |   |               └- 多了一个移动成本
 *      |   └-  3 复制算法 年轻代主要用
 *      |   |       └- 把from区里东西移动到to区，保持一个区为空，
 *      |   |       |  当一个对象经历15此GC 移动到老年代
 *      |   |       |       └- -XX:MaxTenuringThreshold 设定进入老年区的代数
 *      |   |       └- 好处：没有内存碎片
 *      |   |       └- 坏处：多了一半to区空间
 *      |   |       └- 最佳使用场景：对象存活度较低
 *      |   └- 4 引用计数器(用得少)  计数器本身也会有消耗
 *      |           └- 引用链
 *      └- 总结
 *      |   └- 内存效率: 复制算法 > 标记清除算法 > 标记压缩算法
 *      |   └- 内存整齐度: 复制算法 > 标记压缩算法 > 标记清除算法
 *      |   └- 内存利用率: 标记压缩算法 = 标记清除算法 > 复制算法
 *      |   └- 年轻代
 *      |   |     └- 存活率低
 *      |   |     └- 复制算法
 *      └-  └- 老年代
 *                └- 区域大 存活率
 *                └- 标记清除(内存碎片不是太多) + 标记压缩 混合实现
 * 16.JMM
 *      └- Java的并发采用的是共享内存模型
 *      JMM定义了Java 虚拟机(JVM)在计算机内存(RAM)中的工作方式
 *      线程之间的共享变量存储在主内存（Main Memory）中，
 *      每个线程都有一个私有的本地内存（Local Memory），
 *      本地内存中存储了该线程以读/写共享变量的副本
 *
 * @date 2020-06-04
 */
public class _01JVM {
}
