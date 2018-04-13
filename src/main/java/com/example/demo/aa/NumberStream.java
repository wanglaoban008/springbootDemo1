package com.example.demo.aa;

import com.example.demo.entity.DishEntity;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program:  原始类型流特化
 * @description:
 * @author: malili
 * @create: 2018-04-10 11:45
 **/
public class NumberStream {
    /** 
     * @Description: 原始数值流 
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/10 
    */ 
    public static void sum(){
        List<DishEntity> menu = Arrays.asList(
                new DishEntity("鸡腿",false,3, DishEntity.Type.MEAT),
                new DishEntity("鸭腿",false,5, DishEntity.Type.MEAT));
        //这里返回的是int，流操作的是interger然后进行拆箱再sum计算值  可改良
        int calories = menu.stream().map(DishEntity::getCalories).reduce(0,Integer::sum);

        int calories1 = menu.stream()
                .mapToInt(DishEntity::getCalories)  // 返回一个IntStream  而避免了暗含的装箱成本
                .sum();                            // IntStream还支持其他的方便方法，如max、min、average等。
        // 如果流是空的，sum默认返回0
        System.err.println("calories:"+calories);
        System.err.println("calories1:"+calories1);
    }

    /**
     * @Description: 转化成一般流
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/10
    */
    public static void boxed(){
        List<DishEntity> menu = Arrays.asList(
                new DishEntity("鸡腿",false,3, DishEntity.Type.MEAT),
                new DishEntity("鸭腿",false,5, DishEntity.Type.MEAT));
        //原始数值流
        IntStream intStream = menu.stream().mapToInt(DishEntity::getCalories);
        //将数值流转化成一般流
        Stream<Integer> stream =intStream.boxed();
    }
    
    /** 
     * @Description: 对于三种原始流特化，也分别有一个Optional原始类
     *                 型特化版本：OptionalInt、OptionalDouble和OptionalLong
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/10 
    */ 
    public static void optionalInt1(){
        List<DishEntity> menu = Arrays.asList(
                new DishEntity("鸡腿",false,0, DishEntity.Type.MEAT),
                new DishEntity("鸭腿",false,5, DishEntity.Type.MEAT));

        // 这是一个可以表示值存在或不存在的容器
        OptionalInt maxOptional = menu.stream().mapToInt(DishEntity::getCalories).max();
        //如果没有最大值，显示提供一个默认最大值
        int max = maxOptional.orElse(1);
    }
    public static void main(String[] args) {
        sum();
        optionalInt1();
    }
}
