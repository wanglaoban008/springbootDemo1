package com.example.demo.aa;

import com.example.demo.entity.AppleEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-15 14:43
 **/
public class Stream1 {

   public static void aa(){
       List<AppleEntity> appleEntities = new ArrayList<>();
       AppleEntity appleEntity1 = new AppleEntity("yelow","10",300);
       AppleEntity appleEntity2 = new AppleEntity("red","10",100);
       AppleEntity appleEntity3 = new AppleEntity("green","10",50);
       AppleEntity appleEntity4 = new AppleEntity("black","10",200);
       appleEntities.add(appleEntity1);
       appleEntities.add(appleEntity2);
       appleEntities.add(appleEntity3);
       appleEntities.add(appleEntity4);
       List<AppleEntity> appleEntityList = new ArrayList<>();
       for (AppleEntity appleEntity : appleEntities) {
           if (appleEntity.getWeight() < 100){
               appleEntityList.add(appleEntity);
           }
       }
       Collections.sort(appleEntities, new Comparator<AppleEntity>() {
           @Override
           public int compare(AppleEntity o1, AppleEntity o2) {
               return Integer.compare(o1.getWeight(), o2.getWeight());
           }
       });

       appleEntityList.sort(Comparator.comparing(AppleEntity::getWeight));
       List<String> colors = new ArrayList<>();
       for(AppleEntity d: appleEntityList){
           colors.add(d.getColor());
       }
       colors = appleEntities.stream().filter(appleEntity -> appleEntity.getWeight()>50)
               .sorted(Comparator.comparing(AppleEntity::getWeight))
               .map(appleEntity -> appleEntity.getColor()).collect(Collectors.toList());

       // 利用多核架构并行执行
       colors = appleEntities.parallelStream().filter(appleEntity -> appleEntity.getWeight()>50)
               .sorted(Comparator.comparing(AppleEntity::getWeight))
               .map(appleEntity -> appleEntity.getColor()).collect(Collectors.toList());
       System.err.println("colors:"+colors.size());
       colors.forEach(s -> System.err.println("colors:"+s));
       colors.forEach(System.out::println);

   }

    public static void main(String[] args) {
        aa();
    }


}
