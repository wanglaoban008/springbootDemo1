package com.example.demo.test;

import com.example.demo.entity.WelCouponEntity;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-23 13:54
 **/
public class filterTest {
    public static void main(String[] args) {
        //List<WelCouponEntity> couponList =null;
        List<WelCouponEntity> couponList = new ArrayList<>();
        WelCouponEntity welCouponEntity1 = new WelCouponEntity();
        //WelCouponEntity welCouponEntity2 = new WelCouponEntity();
        //WelCouponEntity welCouponEntity3 = new WelCouponEntity();
        //welCouponEntity1.setUseRange("10,20,30");
        //welCouponEntity1.setId(1l);
        //welCouponEntity2.setId(2l);
        //welCouponEntity3.setId(3l);
        //welCouponEntity2.setUseRange("11,20,30");
        //welCouponEntity3.setUseRange("10,11,20,30,40");
        couponList.add(welCouponEntity1);
        //couponList.add(welCouponEntity2);
        //couponList.add(welCouponEntity3);
        welCouponEntity1.getUseRange();
        System.err.println("welCouponEntity1.getUseRange()"+welCouponEntity1.getUseRange());
        couponList = couponList.stream().filter(welCouponEntity ->  {
            //这里是要换成isBlank的
            return !StringUtils.isEmpty(welCouponEntity.getUseRange()) && Arrays.stream(welCouponEntity.getUseRange().split(","))
                    .collect(Collectors.toList()).contains("10");}
        )
                .collect(Collectors.toList());
        couponList.forEach(welCouponEntity -> System.err.println("id："+welCouponEntity.getId()));
    }
}
