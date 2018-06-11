package com.example.demo.test.SingleTonMode;

/**
 * @program:
 * @description:    双重校验锁
 * @author: malili
 * @create: 2018-06-11 16:35
 **/
public class SingletonDemo6 {
    private SingletonDemo6(){}

    private volatile static SingletonDemo6 singletonDemo6;

    public static SingletonDemo6 getSingletonDemo6(){
        if (singletonDemo6 == null){
            synchronized (SingletonDemo6.class){
                if (singletonDemo6 == null){
                    singletonDemo6 = new SingletonDemo6();
                }
            }
        }
        return singletonDemo6;
    }
}
