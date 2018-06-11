package com.example.demo.test.SingleTonMode;

/**
 * @program:
 * @description:  懒汉模式
 * @author: malili
 * @create: 2018-06-07 17:30
 **/
public class SingletonDemo2 {
    private SingletonDemo2() {
    }
    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static SingletonDemo2 instance;
    //方法同步，调用效率低  -- 线程安全
    public static synchronized SingletonDemo2 getInstance(){
        if (instance == null){
            instance = new SingletonDemo2();
        }
        return instance;
    }

    //线程不安全
    //public static SingletonDemo2 getInstance(){
    //    if (instance == null){
    //        instance = new SingletonDemo2();
    //    }
    //    return instance;
    //}
}
