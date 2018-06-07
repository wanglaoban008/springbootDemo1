package com.example.demo.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program:
 * @description:   动态代理拦截器
 * @author: malili
 * @create: 2018-06-07 10:48
 **/
public class MyInterceptor implements InvocationHandler {
    //目标类
    private Object target;

    //构造函数
    public MyInterceptor(Object target) {
        this.target = target;
    }

    /** 
     * @Description:
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/6/7 
    */ 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("aaaa");//切面方法a()
        method.invoke(this.target,args);
        System.err.println("bbbb");//切面方法b()
        return null;
    }

    public static void main(String[] args) {
        //目标对象
        TargetObject targetObject = new TargetObject();
        //拦截器
        MyInterceptor myInterceptor = new MyInterceptor(targetObject);
        /**
         * proxy.newProxyInstance()的参数
         * 1、目标类的加载器
         * 2、目标类的所有接口
         * 3、拦截器
         */
        TargetInterface proxyObj = (TargetInterface) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), myInterceptor);
        proxyObj.sayHello();
    }
}
