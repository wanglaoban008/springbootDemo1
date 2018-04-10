package com.example.demo.aa;

import com.example.demo.entity.AppleEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-04-09 11:03
 **/
public class Reduce1 {
    /** 
     * @Description: 有初始值，求和，求乘积（将数组中的每个元素相加/相乘）
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/9 
    */ 
    public static void reduce1(){
        Integer sum = Arrays.stream(StreamMatch.ints).reduce(0,Integer::sum);
        Integer sum1 = Arrays.stream(StreamMatch.ints).reduce(0,(a,b)->a+b);
        Integer product = Arrays.stream(StreamMatch.ints).reduce(1,(a,b)->a*b);
        System.err.println("sum:"+sum);
        System.err.println("sum1:"+sum1);
        System.err.println("product:"+product);
    }

    /** 
     * @Description:   reduce的重载变体，不接受初始值  返回一个Optional对象
     *                   因为流中没有任何元素所以无法返回其和，因为没有初始值，所以结果包裹在optional对象里，表明和可能不存在
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/9 
    */ 
    public static void reduce2(){
        Optional<Integer> sumO = Arrays.stream(StreamMatch.ints).reduce((a, b)->a+b);
        System.err.println("sumO:"+sumO);
    }

    /**
     * @Description: 通过reduce计算最大值
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void reduceMax(){
        Optional<Integer> max = Arrays.stream(StreamMatch.ints).reduce((x, y) -> x < y ? y : x);
        Optional<Integer> max1 = Arrays.stream(StreamMatch.ints).reduce(Integer::max);
        System.err.println("max:"+max);
        System.err.println("max1:"+max1);
    }

    /**
     * @Description: 通过reduce计算最小值
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void reduceMin(){
        Optional<Integer> min = Arrays.stream(StreamMatch.ints).reduce((x, y) -> x < y ? x : y);
        Optional<Integer> min1 = Arrays.stream(StreamMatch.ints).reduce(Integer::min);
        System.err.println("min:"+min);
        System.err.println("min1:"+min1);
    }

    /**
     * @Description: 计算一共有多少种苹果   map和reduce一起用
     *                   map-reduce模式   容易并行化 也可以用count来计算
     *                   Stream换成parallelStream 就可以实现并行
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void reduceApple(){
        AppleEntity apple1 = new AppleEntity("red","10",1);
        AppleEntity apple2 = new AppleEntity("green","5",2);
        AppleEntity apple3 = new AppleEntity("yellow","6",3);
        AppleEntity apple4 = new AppleEntity("red","7",1);
        List<AppleEntity> appleEntityList = new ArrayList<>();
        appleEntityList.add(apple1);
        appleEntityList.add(apple2);
        appleEntityList.add(apple3);
        appleEntityList.add(apple4);
        Integer sum = appleEntityList.stream().map(appleEntity -> 1).reduce(0,Integer::sum);
        long count = appleEntityList.stream().count();
        System.err.println("一共有:"+sum+"种苹果");
        System.err.println("一共有:"+count+"种苹果(用count计算出）");
    }

    public static void main(String[] args) {
        reduce1();
        reduce2();
        reduceMax();
        reduceMin();
        reduceApple();
    }
}
