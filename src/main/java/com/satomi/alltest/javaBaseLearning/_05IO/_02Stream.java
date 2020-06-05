package com.satomi.alltest.javaBaseLearning._05IO;

import org.junit.Test;

import java.io.*;

/**
 * @author nasazumi
 * @description
 *      输入 input 读取外部数据(磁盘 光盘等存储设备到内存中)
 *      输出 output 将程序(内存)中的数据输出到磁盘等存储设备
 * @date 2020-06-03
 */
public class _02Stream {
    /**
     * 流的分类
     *   | └- 数据单位不同
     *   | |    └- 字节流 8bit  非文本的文件使用
     *   | |    └- 字符流 16bit
     *   | └- 流的角色不同
     *   |      └- 节点流 直接作用在文件(数据)上
     *   |      └- 处理流 作用在已有的流上(又包了一层流)
     *   └- 访问文件
     *   |     └- 字节输入流
     *   |          └- FileInputStream
     *   |     └- 字节输出流
     *   |          └- FileOutputStream
     *   |     └- 文件输入流
     *   |          └- FileReader
     *   |     └- 文件输出流
     *   └----------└- FileWriter
     *   └- 缓冲流
     *   |     └- 字节输入流
     *   |          └- BufferedInputStream
     *   |     └- 字节输出流
     *   |          └- BufferedOutputStream
     *   |     └- 文件输入流
     *   |          └- BufferedReader
     *   |     └- 文件输出流
     *   └----------└- BufferedWriter
     *   └- 对象流
     *   |     └- 字节输入流
     *   |          └- ObjectInputStream
     *   |     └- 字节输出流
     *   └----------└- ObjectOutputStream
     *   └- 转换流
     *   |     └- 文件输入流
     *   |          └- InputStreamReader
     *   |     └- 文件输出流
     *   └----------└- OutputStreamdWriter
     *
     *
     *   标准输入输出流
     *      System 可以通过System类的setIn()/setOut() 重新指定输入输出的流
     *          └- System.out 控制台输出 printStream
     *          └- System.in
     *              |  └- 键盘输入
     *              |        └- 使用Scanner方法 调用next() 返回一个字符串
     *              |        └- 使用System.in --> 转换流 --> BufferedReader的readLine()
     *              |  └- new InputStreamReader(System.in)  转换流
     *              |  |  new BufferedReader(inputStreamReader)
     *              |  |  String data = bufferedReader.readLine()
     *              |  |  if("e".equalsIngorCase(data) || "exit".equalsIngorCase(data))
     *              |  └- 程序结束
     *              |
     *              └--- System.out就被包装成PrintStream流,
     *              |    System.err也是PrintStream流,
     *              |    注意System.in不是PrintStream,
     *              └--- 是没有包装过的OutputStream.所以System.in不能直接使用.
     *   打印流
     *      └- printStream
     *      继承了FilterOutputStream.是"装饰类"的一种
     *          └-PrintStream流永远不会抛出异常.因为做了try{}catch(){}会将异常捕获,
     *          | 出现异常情况会在内部设置标识,通过checkError()获取此标识
     *          \ PrintStream流有自动刷新机制,
     *          └-例如当向PrintStream流中写入一个字节数组后自动调用flush()方法.
     */

    @Test
    public void test(){
        File file = new File("hello.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            /**
             *  1 读入 返回一个读入的字符，如果达到文件末尾返回 -1
             *  2 循环读取
             *  3 关闭流
             *
             *     不推荐抛出异常，代码没有到close()前出现异常，会导致后面的代码不执行
             *     close(）代码不执行，流不会关闭可能会导致内存泄漏问题
             */
            int data = fileReader.read();
            while (data != -1) {
                System.out.println((char)data);
                data = fileReader.read() ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != fileReader)
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 缓冲流的使用
     *      └-作用 提高流的读取 写入速度
     */
    @Test
    public void test1(){
        /**
         *  1 创建文件对象
         *  2 创造节点流
         *  3 创造缓冲流
         *      BufferedInputStream(InputStream in)
         *          └- this(in , DEFAULT_BUFFER_SIZE(8192))
         *          └- 内部提供一个缓冲区，等到缓冲区满了一次性进行输出 flush()，提高能能
         *  4 复制的细节: 读取 写入
         *  5 资源关闭
         *       └- 先关闭外层再关闭内层
         *       └- 关闭外层时，内层会自动关闭，代码可以省略
         */
        BufferedInputStream bis = null ;
        BufferedOutputStream bos = null ;
        try {
            //com/satomi/javaBaseLearning/IO/mei.png
            File srcFile = new File("src/main/java/com/satomi/javaBaseLearning/IO/mei.png") ;
            File destFile = new File("src/main/java/com/satomi/javaBaseLearning/IO/mei2.png") ;

            System.out.println("absolutePath" + srcFile.getAbsolutePath());
            System.out.println("path" + srcFile.getPath());

            FileInputStream fis = new FileInputStream(srcFile) ;
            FileOutputStream fos = new FileOutputStream(destFile) ;

            bis = new BufferedInputStream(fis) ;
            bos = new BufferedOutputStream(fos) ;

            byte[] buffer = new byte[10] ;
            int len ;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                //bos.flush(); //刷新缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != bos) {
                try {
                    bos.close() ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != bis) {
                try {
                    bis.close() ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *  对象流
     *     └- 序列化
     *          └- 将内存重的java对象保存磁盘中或通过网络发送出去
     *     └- 反序列化
     *          └- 反之
     *     └- 要想java对象可序列化，需要满足下列要求
     *              └- Serializable(IO包下) 标示接口
     *              └- Externalizable
     */
    @Test
    public void test02() {
        //序列化
        ObjectOutputStream oos = null ;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat")) ;
            oos.writeObject(new Person("nala", 17));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
