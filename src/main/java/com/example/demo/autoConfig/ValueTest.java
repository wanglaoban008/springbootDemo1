package com.example.demo.autoConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:
 * @description:  这里直接用@Value 就直接自动配置了
 * @author:  malili
 * @create: 2018-06-05 14:37
 **/
@RestController
public class ValueTest {
    @Value("${valueTest.url.testHello}")
    private String testHello;
    @Value("${valueTest.url.testMe}")
    private String testMe;

    @RequestMapping(value = "index")
    public String sayHello(){
        System.err.println("我今天和一个帅哥say"+testHello);
        return "我今天和一个帅哥say"+testHello;
    }


}
