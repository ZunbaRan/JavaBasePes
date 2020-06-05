package com.satomi.alltest.javaBaseLearning._07Reflection;

/**
 * @author nasazumi
 * @description
 *      静态代理
 *          代理类和被代理类在编译期间就确定下来了
 * @date 2020-06-03
 */
public class _02StaticProxy {
    public static void main(String[] args) {
        //创建被代理类对象
        NikeClothFactory nikeClothFactory = new NikeClothFactory() ;
        //创建代理类对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory) ;
        proxyClothFactory.productCloth();
    }
}

interface ClothFactory {
    void productCloth();
}
//代理类
class ProxyClothFactory implements ClothFactory {
    //用被代理队形进行实例化
    private ClothFactory factory ;

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory ;
    }

    @Override
    public void productCloth() {
        System.out.println("代理工厂做准备工作");

        factory.productCloth();
        System.out.println("代理工厂做后续工作");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory {

    @Override
    public void productCloth() {
        System.out.println("Nike工厂生产");
    }
}