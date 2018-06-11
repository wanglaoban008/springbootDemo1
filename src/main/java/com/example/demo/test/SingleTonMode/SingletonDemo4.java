package com.example.demo.test.SingleTonMode;

/**
 * @program:
 * @description:    内部静态类
 *                    同样采用了classloader保证初始化instance只有一个线程
 *                    相比饿汉模式 和 饿汉变种模式 而言更好，
 *                    因为初始化sigletonDemo4的时候不会主动实例化instance
 *                    如果实例化instance很消耗资源，想让他延迟加载，另一方面不确定别的地方使用了singleton被加载，
 *                    这时实例化instance是不合适的
 * @author: malili
 * @create: 2018-06-11 16:10
 **/
public class SingletonDemo4 {
    private SingletonDemo4(){
    }

    private static class SingletonHolder{
      private static SingletonDemo4 instance = new SingletonDemo4();
    }

    public static SingletonDemo4 getInstance(){
        return SingletonHolder.instance;
    }
}
