package com.example.demo.test.SingleTonMode;

/**
 * @program:
 * @description:    枚举  能避免多线程同步问题还能防止反序列化重新创建新的对象
 * @author: malili
 * @create: 2018-06-11 16:32
 **/
public enum SingletonDemo5 {
    instance;
    public void whateverMethod(){

    }
}
