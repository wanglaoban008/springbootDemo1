package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-05-23 10:55
 **/
public class listTest {
    public static void main(String[] args) {
        List<String> aa = null;
        List<String> bb = new ArrayList<>();
        String b1="b1";
        String b2="b2";
        String b3="b3";
        bb.add(b1);
        bb.add(b2);
        bb.add(b3);
        List<String> cc = new ArrayList<>();
        //cc.addAll(aa);  //这里会报错
        cc.addAll(bb);
        System.err.println("cc:"+cc);
    }
}
