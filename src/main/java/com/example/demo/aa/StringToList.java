package com.example.demo.aa;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: StringToList
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-27 11:55
 **/
public class StringToList {
    public static void main(String[] args) {
        String str = "";
        List<String> phones = Arrays.stream(str.split(",")).collect(Collectors.toList());
        System.err.println("phones:"+phones);
    }
}
