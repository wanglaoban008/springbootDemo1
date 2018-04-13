package com.example.demo.aa;

import java.util.stream.Stream;

/**
 * @program:
 * @description:  由值创建流 Stream.of
 * @author: malili
 * @create: 2018-04-13 16:37
 **/
public class ValueOfStream {

    /**
     * @Description: 创建一个数值流
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/13
    */
    public static void createStream1(){
        //创建字符串流
        Stream<String> stringStream = Stream.of("MoBeiBei","gulala1","xiaoli2","san4");
        //先将字符串转化成大写  然后打印
        stringStream.map(s -> s.toUpperCase()).forEach(System.out::println);
    }

    /**
     * @Description: 创建一个空流
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/13
    */
    public static void createEmptyStream(){
        Stream<String> emptyStream = Stream.empty();
        emptyStream.forEach(em -> System.err.println("em:"+em));
    }

    public static void main(String[] args) {
        createStream1();
        createEmptyStream();
    }
}
