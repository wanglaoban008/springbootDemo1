package com.example.demo.aa;

import java.util.Arrays;
import java.util.Optional;

/**
 * @program: demo
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-29 15:37
 **/
public class Find {

    /** 
     * @Description: findAny方法将返回当前流中的任意元素
     *                 流水线将在后台进行优化使其只需走一遍，并在利用短路找到结果时立即结束
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/3/29 
    */ 
    public static void findAny1(){
        Optional<Integer> in = Arrays.stream(StreamMatch.ints).filter(integer -> integer > 2).findAny();
        System.err.println("in:"+in);
        Optional<Integer> in1 = Arrays.stream(StreamMatch.ints).findAny();
        System.err.println("in1:"+in1);


    }
    /**
     * @Description: 查找第一个能被3整除的数
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void findFirst1(){
        Optional<Integer> int3 = Arrays.stream(StreamMatch.ints).filter(integer -> integer % 3 == 0).findFirst();
        Optional<Integer> int31 = Arrays.stream(StreamMatch.ints).map(integer -> integer * integer).filter(integer -> integer % 3 == 0).findFirst();
        System.err.println("int3:"+int3);
        System.err.println("int31:"+int31);
    }

    public static void main(String[] args) {
        findAny1();
        findFirst1();
    }
    
}
