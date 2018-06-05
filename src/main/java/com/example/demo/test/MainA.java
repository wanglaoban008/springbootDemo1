package com.example.demo.test;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-05-02 15:09
 **/
public class MainA {

    /** 
     * @Description:   测试类的实例化顺序 
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/5/2 
    */ 
    public static void main(String[] args) {
        System.out.println("main app run..");
        BGoZao b = new BGoZao();
//      B b = new B(22);
        b.methodA();
    }



}
