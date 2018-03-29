package com.example.demo.aa;

import java.util.Arrays;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-29 10:27
 **/
public class StreamMatch {
    public static final Integer[] ints= {1,2,3,4,5};

    /** 
     * @Description: 流中是否有一个元素能匹配给定的谓词 返回的是布尔类型
     * @Param:  
     * @return:
     * @Author: malili 
     * @Date: 2018/3/29 
    */ 
    public static void anyMatch1(){
        if (Arrays.stream(ints).anyMatch(integer -> integer==3)){
            System.err.println("数组中有包含3的");
        }

    }
    
    /** 
     * @Description: 检查谓词是否匹配所有元素
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/3/29 
    */ 
    public static void allMatch1(){
        if (Arrays.stream(ints).allMatch(integer -> integer < 10)){
            System.err.println("数组的元素每个都小于10");
        }
        if (Arrays.stream(ints).allMatch(integer -> integer > 0)){
            System.err.println("数组的元素每个都大于0");
        }
        if (Arrays.stream(ints).allMatch(integer -> integer > 3)){
            System.err.println("数组的元素每个都大于3");
        }
    }

    /** 
     * @Description: 流中没有任何元素与给定的谓词匹配  和allMatch相对的是noneMatch
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/3/29 
    */ 
    public static void noneMatch1(){
        if (Arrays.stream(ints).noneMatch(integer -> integer > 10)){
            System.err.println("数组中的元素没有大于10的");
        }
        if (Arrays.stream(ints).noneMatch(integer -> integer < 0)){
            System.err.println("数组中的元素没有小于0的");
        }
    }

    public static void main(String[] args) {
        anyMatch1();
        allMatch1();
        noneMatch1();
    }
}
