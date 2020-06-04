package com.satomi.alltest.javaBaseLearning.IO;

import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * @author nasazumi
 * @description
 *  java.io
 *      └- File
 *          └- public File(String pathname)
 *          |  public File(String parent, String child)
 *          └- public File(File Parent, String child)
 *
 * @date 2020-06-02
 */
public class _01File {

    @Test
    public void test01() {
        /**
         * 相对于当前moudle的路径
         *  windows 和 DOS 默认"\"
         *  UNIX 和 linux 使用 "/"
         *  File类提供分隔符常量
         *      └- public static final String separator
         *      new File("d" + File.separator + "hello.txt")
         */
        File file = new File("hello.txt");
    }

    @Test
    public void test02() {
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(new Date(file.lastModified()));
        System.out.println(file.getParent()); //相对路径下上级文件目录
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println("------------------------");

        /**
         *  public String[] list() 获取指定目录下所有文件或目录
         *  public File[] listFiles() 获取指定目录下所有文件或目录的File数组
         */
        File file2 = new File(File.separator + "Users" + File.separator + "satomi" + File.separator + "Documents") ;
        String[] list = file2.list();
        for (String s: list) {
            System.out.println(s);
        }

        File[] files = file2.listFiles() ;
        for (File f: files) {
            System.out.println(f);
        }
        /**
         * public boolean isDirectory()
         * boolean isFile()
         * boolean exists()
         * boolean canRead()
         * boolean canWrite()
         * boolean isHidden()
         */
    }


}
