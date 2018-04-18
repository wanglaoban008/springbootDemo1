package com.example.demo.ZaLuanTest;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-04-16 18:05
 **/
public class SubString1 {

    /**
     * @Description: 截取字符串
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/16
     */
    public static void main(String[] args) {
        String str = "miaoyinlicai_test_0_v1.1.1.apk";
        //这里区分str.lastIndexOf，和str.indexOf
        int start = str.lastIndexOf(".");//返回最后一个出现的位置
        int end = str.length();
        String string = str.substring(str.lastIndexOf(".") + 1, str.length());
        System.err.println("string:"+string);
    }
}
