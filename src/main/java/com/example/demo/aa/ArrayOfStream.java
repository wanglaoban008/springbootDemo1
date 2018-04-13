package com.example.demo.aa;

import java.util.Arrays;

/**
 * @program:
 * @description: 由数组创建流
 * @author: malili
 * @create: 2018-04-13 16:46
 **/
public class ArrayOfStream {
    /** 
     * @Description: 使用静态方法 由数组创建一个流
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/13 
    */ 
    public static void arrayStream1(){
        int[] array = {1,2,3,4,5,6};
        int sum = Arrays.stream(array).sum();
        System.err.println("sum:"+sum);
    }

    public static void main(String[] args) {
        arrayStream1();
    }
}
