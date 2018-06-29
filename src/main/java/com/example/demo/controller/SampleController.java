package com.example.demo.controller;

import com.example.demo.autoConfig.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program:
 * @description:   第一个简单的controller
 * @author: malili
 * @create: 2018-04-24 11:23
 **/
@Controller
@EnableAutoConfiguration
public class SampleController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "Hello World";
    }

    @GetMapping("/html")
    public String html(){
        return "/aa.html";
    }



    /** 
     * @Description:  这里是自动配置的deomo
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/24 
    */ 
    @RequestMapping("/hello1")
    @ResponseBody
    public String index(){
        return helloService.sayHello();
    }

    //public static void main(String[] args) {
    //    SpringApplication.run(SampleController.class,args);
    //}

}
