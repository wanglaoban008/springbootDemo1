package com.example.demo.DynamicProxy;

/**
 * @program:
 * @description:  目标类，实现目标接口，继而实现目标方法
 * @author: malili
 * @create: 2018-06-07 10:47
 **/
public class TargetObject implements TargetInterface{

    @Override
    public void sayHello() {
        System.err.println("hello man~");
    }
}
