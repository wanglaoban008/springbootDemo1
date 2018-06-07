package com.example.demo.CglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program:
 * @description:   cglib动态代理-拦截器
 * @author: malili
 * @create: 2018-06-07 11:24
 **/
public class MyInterceptorTwo implements MethodInterceptor {
    private Object target;//目标类

    public MyInterceptorTwo(Object target) {
        this.target = target;
    }

    /**
     * 返回代理对象
     * @return
     */
    public Object createProxy(){
        Enhancer enhancer = new Enhancer();
        //回调函数 拦截器
        enhancer.setCallback(this);
        //设置代理对象的父类，代理对象是目标对象的子类，这个接口类可以直接省略了
        enhancer.setSuperclass(this.target.getClass());
        return enhancer.create();
    }

    /** 
     * @Description:  args 目标方法的参数
     *                  method 目标方法
     * @Param:
     * @return:  
     * @Author: malili 
     * @Date: 2018/6/7 
    */ 
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("AAAAAA");//切面方法a()
        //调用目标类的目标方法
        method.invoke(this.target,objects);
        System.err.println("BBBBBB");//切面方法b()
        return null;
    }

    /** 
     * @Description:
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/6/7 
    */ 
    public static void main(String[] args) {
        //目标对象
        TargetObjectTwo targetObjectTwo = new TargetObjectTwo();
        //拦截器
        MyInterceptorTwo myInterceptorTwo = new MyInterceptorTwo(targetObjectTwo);
        //代理对象，调用cglib系统方法自动生成
        //代理类是目标类的子类
        TargetObjectTwo target = (TargetObjectTwo) myInterceptorTwo.createProxy();
        target.sayHello2();
    }
}
