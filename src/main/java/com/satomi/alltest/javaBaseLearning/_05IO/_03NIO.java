package com.satomi.alltest.javaBaseLearning._05IO;

/**
 * @author nasazumi
 * @description
 *      New IO  Non-Blocking IO
 *      面向缓冲区，能以更高效的方式进行文件的读写
 *      java API提供两套NIO，一套针对标准输入输出NIO，一套针对网络编程
 *          |-- java.nio.channels.Channel
 *            └- FileChannel 处理本地文件
 *            └- SocketChannel TCP网络编程客户端的Channel
 *            └- ServerSocketChannel 服务端的Channel
 *            └- DatagramChannel UDP网络编程发送端和接收端的Channel
 *
 *      NIO.2 --> 方法出错返回错误信息
 *         └- Path
 *              └- 可以看成是File类的升级版本
 *         └- Paths
 *         └- Files
 *              └- 文件操作的工具类
 *
 * @date 2020-06-03
 */
public class _03NIO {
}
