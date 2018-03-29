package com.example.demo.aa;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-16 10:51
 **/
public class UseStream {

    /**
     * @Description: 使用流，stream api可以并行运行代码  介绍一些流的方法
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/3/16
     */
    public static void example() {

        List<Integer> aa  = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 3).
                stream().
                filter(integer -> integer > 1).//谓词筛选
                distinct().//去重筛选
                limit(9).//截短流
                collect(Collectors.toList());

        List<Integer> bb  = Arrays.asList(7,8,9,10,7,11,12).
                stream().
                filter(integer -> integer > 1).//谓词筛选
                distinct().//去重筛选
                skip(2).//跳过  和limit是互补的
                collect(Collectors.toList());

        System.err.println("aa:"+aa);
        System.err.println("bb:"+bb);

    }
    
    /** 
    * @Description:  映射 map 还有flatMap example4会介绍
    * @Param:  
    * @return:  
    * @Author: malili 
    * @Date: 2018/3/16 
    */ 
    public static void  example1(){
        List<String> strings = Arrays.asList(1,2,3,4,5,6).stream().
                filter(integer -> integer>1).//筛选
                //映射
                map(integer -> String.valueOf(integer)).
                collect(Collectors.toList());
        System.err.println("strings:"+strings);
    }

    /**
    * @Description: 不使用stream解决数组["Hello", "World"] 转变成 ["H","e","l", "o","W","r","d"]
    * @Param:
    * @return:
    * @Author: malili
    * @Date: 2018/3/16
    */
    public static void example2() {
        Set<String> str = new LinkedHashSet<String>();
        //List<String> aa = Arrays.asList("Hello", "World");
        //for (String s : aa) {
        //    for (int i = 0; i < s.length(); i++) {
        //        char item = s.charAt(i);
        //        str.add(String.valueOf(item));
        //    }
        //}
        //System.err.println("str:" + str.size());
        //str.forEach(System.err::print);

        String[] cc = {"Hello", "World"};
        System.err.println("str"+str.size());
        Arrays.stream(cc).forEach(s -> {
            for (int i = 0; i < s.length(); i++) {
                char item = s.charAt(i);
                str.add(String.valueOf(item));
            }
        });
        System.err.println("str:"+str.size());
        System.err.println("str:"+str);
        //str.stream().forEach(System.err::print);


    }
    /** 
    * @Description: map方法  尝试使用map和Arrays.stream()
    * @Param:
    * @return:
    * @Author: malili 
    * @Date: 2018/3/16 
    */ 
    public static void example3(){
        String[] arrayOfWords = {"Goodbye", "World"};
        List<Stream> aa = Arrays.stream(arrayOfWords).
                map(s -> s.split("")).
                //让每个数组变成一个单独的流
                map(Arrays::stream).
                distinct().collect(Collectors.toList());
        System.err.println("stream:"+aa.size());
        System.err.println("stream:"+aa);
        //aa.forEach(System.err::print);

    }

    /**
    * @Description: flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。
    *                 所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流
    *                 flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连起来成为一个流
    * @Param:
    * @return:
    * @Author: malili
    * @Date: 2018/3/16
    */
    public static void example4(){
        String[] arrayOfWords = {"Goodbye", "World"};
        List<String> bb = Arrays.stream(arrayOfWords).
                map(s -> s.split("")).
                //将各个生成流扁平化为单个流
                flatMap(Arrays::stream).
                distinct().collect(Collectors.toList());
        System.err.println("stream1:"+bb.size());
        //bb.forEach(System.err::print);
        System.err.println("stream1:"+bb);
        //Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
    }

    /**
    * @Description: 练习1
    * @Param:
    * @return:
    * @Author: malili
    * @Date: 2018/3/16
    */
    public static void example5(){
        Integer[] intNumber = {1, 2, 3, 4, 5};
        List<Integer> doubleNumber = Arrays.stream(intNumber).
                map(integer -> integer*integer).collect(Collectors.toList());
        System.err.println("doubleNumber:"+doubleNumber.size());
        //doubleNumber.forEach(System.err::print);
        System.err.println("doubleNumber:"+doubleNumber);
    }

    /** 
    * @Description: 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
    该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
    表数对。
                       这是生成六个数组，把六个数组加到list中，只返回总和能被3整除的
                       这个flatmap 有点不会用，只能理解，不太会用
    * @Param:  
    * @return:  
    * @Author: malili 
    * @Date: 2018/3/16 
    */ 
    public static void example6(){
        List<Integer> a = Arrays.asList(1,2,3);
        List<Integer> b = Arrays.asList(3,4);
        List<int[]> c = a.stream().
                flatMap(integer -> b.stream().map(integer1 -> new int[]{integer,integer1})).
                collect(Collectors.toList());
        System.err.println("c:"+c.size());
        //Arrays的toString方法是返回指定数组内容的字符串表示形式
        c.stream().forEach(ints -> System.err.println("int:"+Arrays.toString(ints)));
        //http://blog.csdn.net/csdn_misli/article/details/53081373
        //a.toString()和Arrays.toString(a)的区别
        //Object 类的 toString 方法返回一个字符串，该字符串由类名（对象是该类的一个实例）加上“@”和此对象哈希码的无符号十六进制表示组成

        //在上面的基础上只返回总和能被3整除的数对  例如(2, 4)和(3, 3)是可以的
        List<Integer> e = Arrays.asList(1,2,3);
        List<Integer> f = Arrays.asList(3, 4);
        List<int[]> g = e.stream().
                flatMap(integer -> f.stream().filter(integer1 -> (integer + integer1) % 3 == 0).map(integer1 -> new int[]{integer, integer1})).
                collect(Collectors.toList());
        System.err.println(" g:"+c.size());
        //Arrays的toString方法是返回指定数组内容的字符串表示形式
        g.stream().forEach(ints -> System.err.println("intg:"+Arrays.toString(ints)));
    }

    public static void main(String[] args) {
        example();
        System.err.println("=====================");
        example1();
        System.err.println("=====================");
        example2();
        System.err.println("=====================");
        example3();
        System.err.println("=====================");
        example4();
        System.err.println("=====================");
        example5();
        System.err.println("=====================");
        example6();
    }
}
