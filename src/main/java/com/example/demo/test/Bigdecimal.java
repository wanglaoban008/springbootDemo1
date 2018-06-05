package com.example.demo.test;

import java.math.BigDecimal;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-05-13 17:33
 **/
public class Bigdecimal {

    public static void main(String[] args) {
        BigDecimal b = new BigDecimal(35);
        BigDecimal bc = null;
        Double cc = 25d;
        if (bc.compareTo(new BigDecimal(cc)) < 0){
            System.err.println(bc+"<"+cc);
        }else {
            System.err.println(bc+">="+cc);
        }
    }
}
