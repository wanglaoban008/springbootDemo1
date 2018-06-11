package com.example.demo.test.SingleTonMode;

/**
 * @program:
 * @description:  饿汉模式--变种
 *                  基于classloader避免了多线程同步问题，但是没达到延时加载的效果
 * @author: malili
 * @create: 2018-06-11 16:07
 **/
public class SingletonDemo3 {
    private SingletonDemo3(){

    }
    private static SingletonDemo3 instance = null;
    static {
        instance = new SingletonDemo3();
    }
    public static SingletonDemo3 getSingleton(){
        return instance;
    }
}
