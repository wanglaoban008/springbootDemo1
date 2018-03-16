package com.example.demo.aa;

import com.example.demo.entity.DishEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-15 16:16
 **/
public class Stream2 {
    /** 
    * @Description: 流只能消费一次
    * @Param:  
    * @return:  
    * @Author: malili 
    * @Date: 2018/3/15 
    */ 
    public static void streamForech() {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        //这里会报错，流只能操作一次，这次遍历操作流，流已经关闭了
        s.forEach(System.out::println);
    }

    /** 
    * @Description: 内部迭代 stream api可以并行运行代码
    * @Param:  
    * @return:  
    * @Author: malili 
    * @Date: 2018/3/15 
    */ 
    public static void internalIteration(){
        List<String> names = new ArrayList<>();
        List<DishEntity> menu = haveList();
        //names = menu.stream().map(DishEntity::getName).collect(Collectors.toList());
        names = menu.stream().filter(dishEntity -> {
            System.err.println("filter:"+dishEntity.getName());
            return dishEntity.getCalories()>50;
        }).map(dishEntity -> {
            System.err.println("map:"+dishEntity.getName());
            return dishEntity.getName();
        }).limit(2).collect(Collectors.toList());
        //过滤filter（筛选），去重distinct（筛选），截短limit
        long count = menu.stream().filter(dishEntity -> {
            System.err.println("filter:"+dishEntity.getName());
            return dishEntity.getCalories()>100;
        }).distinct().limit(8).count();

        System.err.println(names);
        System.err.println("count:"+count);
    }

    /** 
    * @Description: 外部迭代 forech  单一线程 挨个迭代
    * @Param:  
    * @return:  
    * @Author: malili 
    * @Date: 2018/3/15 
    */ 
    public void externalIteration(){
        List<String> names = new ArrayList<>();
        List<DishEntity> menu = haveList();
        for(DishEntity d: menu){
            names.add(d.getName());
        }
    }
    /**
    * @Description: 外部迭代 迭代器
    * @Param:
    * @return:
    * @Author: malili
    * @Date: 2018/3/15
    */
    public void externalIterationItera(){
        List<String> names = new ArrayList<>();
        List<DishEntity> menu = haveList();
        Iterator<DishEntity> iterator = menu.iterator();
        while (iterator.hasNext()){
            DishEntity d = iterator.next();
            names.add(d.getName());
        }
    }

    /**
    * @Description: 初始化list 供调用
    * @Param:
    * @return:
    * @Author: malili
    * @Date: 2018/3/15
    */
    public static List<DishEntity> haveList(){
        List<DishEntity> menu = new ArrayList<>();
        DishEntity dishEntity1 = new DishEntity("sucai1",true,50,DishEntity.Type.MEAT);
        DishEntity dishEntity2 = new DishEntity("sucai2",true,200,DishEntity.Type.FISH);
        DishEntity dishEntity3 = new DishEntity("sucai3",true,300,DishEntity.Type.OTHER);
        DishEntity dishEntity4 = new DishEntity("sucai4",true,400,DishEntity.Type.FISH);
        DishEntity dishEntity5 = new DishEntity("sucai5",true,500,DishEntity.Type.FISH);
        menu.add(dishEntity1);
        menu.add(dishEntity2);
        menu.add(dishEntity2);
        //menu.add(dishEntity3);
        menu.add(dishEntity4);
        menu.add(dishEntity5);
        return menu;
    }

    public static void main(String[] args) {
        internalIteration();
    }
    //这边有问题不知道为啥按理说我limit为2，filter和map都应该只打印两个出来，为啥filter打印了三个
//    filter:sucai1
//    filter:sucai2
//    map:sucai2
//    filter:sucai3
//    map:sucai3
//[sucai2, sucai3]
}
