package com.example.demo.test;

/**
 * @program:
 * @description:   类的实例化顺序
 * @author: malili
 * @create: 2018-05-02 14:31
 **/
public class GoZao {

    int a1 = 8;
    int a2 = getA2();//7
    {
        int a3 = 9;
        System.out.println("top of A() a1=" + a1 + " a2=" + a2 + "  a3=" + a3);
    }

    public GoZao() {
        this(66);
        System.out.print("A 构造函数\n");
    }

    {
        System.out.println("below A()..has start");
    }

    public GoZao(int num) {
        System.out.print("A 带参数构造函数: " + num + "\n");
    }

    static {
        System.out.println("I`m a static {} from class A..");
    }

    int getA2() {
        System.out.println("getA2..");
        return 7;
    }

    public void methodA() {
        System.out.println("methodA");
    }
}
