package com.satomi.alltest.javaBaseLearning.Socket;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author nasazumi
 * @description
 *      TCP/IP 参考模型
 *          └- 应用层 http DNS
 *          └- 传输层 TCP / UDP
 *          └- 网络层 IP
 *          └- 物理层 + 数据链路层
 *      IP
 *       └- ip 唯一的标识Internet的计算机
 *       └- Java中使用InetAddress类代表IP
 *       └- 分类IPv4 IPv6
 *           域名解析服务器
 *                |
 *       域名 --> DNS -->
 *
 * @date 2020-06-03
 */
public class _01Inet {

    @Test
    public void test() {
        try {
            InetAddress localhost1 = InetAddress.getByName("localhost");
            System.out.println(localhost1);

            InetAddress localhost2 = InetAddress.getByName("www.vip.com");
            System.out.println(localhost2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
