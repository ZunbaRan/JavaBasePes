package com.satomi.alltest.javaBaseLearning._07Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-03
 */
public class _03Proxy {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan() ;
        Human proxy = (Human)ProxyFactory.getProxyInstance(superMan);
        String belief = proxy.getBelief();
        System.out.println(belief);
        proxy.eat("汉堡🍔");
    }
}

interface Human {
    String getBelief() ;
    void eat(String food) ;
}

//被代理类
class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "I believe I can fly !";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }

}

//动态代理类
/**
 *  1 如何更具加载到内存中的被代理类，动态的创建一个代理类及其对象
 *  2 通过代理类的对象调用方法是，如何动态的去调用被代理类的同名方法
 */
class ProxyFactory{
    //调用此方法，调用代理类对象，解决问题1
    static public Object getProxyInstance(Object obj) { //obj 被代理的对象
        MyInvocationHandler handler = new MyInvocationHandler() ;
        //绑定被代理的对象
        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                                obj.getClass().getInterfaces(),
                                handler) ;
    }
}

class MyInvocationHandler implements InvocationHandler{

    private Object obj ;
    //绑定被代理的对象
    public void bind(Object obj){
        this.obj = obj ;
    }

    //当我们通过代理类的对象调用方法a时，就会自动的调用如下方法 invoke()
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //obj 被代理的对象
        System.out.println("Method method" + method);
        System.out.println("args" + args);
        Class<?> aClass = proxy.getClass();
        System.out.println("aClass" + aClass);
        Object returnValue = method.invoke(obj, args);
        return returnValue;
    }
}