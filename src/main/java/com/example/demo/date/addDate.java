package com.example.demo.date;

import com.example.demo.util.DateUtils;

import java.util.Date;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-26 17:50
 **/
public class addDate {
    public static void main(String[] args) {
        long a = DateUtils.getTodayPointInTime(0).getTime();
        long b = System.currentTimeMillis();
        System.err.println("today:"+a);
        System.err.println("toa:"+new Date(a));
        System.err.println("tob:"+new Date(b));
        System.err.println("guoqitime:"+new Date(a+2 * 24 * 60 * 60 * 1000L - 1));
        System.err.println("guoqitimeB:"+new Date(b+2 * 24 * 60 * 60 * 1000L - 1));
    }
}
