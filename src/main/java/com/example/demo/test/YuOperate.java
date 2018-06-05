package com.example.demo.test;

/**
 * @program:
 * @description:  与操作
 * @author: malili
 * @create: 2018-05-07 11:08
 **/
public class YuOperate {

    public static void main(String[] args) {
        int index = 8%3;
        int index1 = 3&8;
        System.err.println("取模运算值："+index);
        System.err.println("与操作值："+index1);
        int a = 1<<30;
        System.err.println("1左移30位变成："+a);
    }
}
