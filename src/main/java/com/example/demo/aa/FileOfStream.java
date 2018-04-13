package com.example.demo.aa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @program:
 * @description:  Files.lines得到一个流
 * @author: malili
 * @create: 2018-04-13 17:10
 **/
public class FileOfStream {
    /** 
     * @Description:   Files.lines，它会返回一个由指定文件中的各行构成的字符串流 
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/13 
    */ 
    public static void fileLine(){
       long uniqueWords = 0;
       //这里注意到了没：try(){}catch{}  是第一次遇到try后面还有参数的吧，这个是java7新特性，用于释放资源
       // try括号内的资源会在try语句结束后自动释放，前提是这些可关闭的资源必须实现 java.lang.AutoCloseable 接口。
        //Files.lines 这个方法可以直接读取文档中的内容
       try (Stream<String> lines = Files.lines(Paths.get("D:\\word.txt"), Charset.defaultCharset())){

           uniqueWords = lines
                   .flatMap(s -> Arrays.stream(s.split(""))) //生成一个扁平的单词流
                   .distinct()  // 去重
                   .count();    //数一数共有多少个不同的单词

           System.err.println("uniqueWords:"+uniqueWords);

           //Stream<String> li = lines.flatMap(s -> Arrays.stream(s.split(""))).distinct();
           //System.err.println("li:"+li);

       }catch (IOException e){
           e.printStackTrace();
            //处理异常
       }
    }

    public static void main(String[] args) {
        fileLine();
    }
}
