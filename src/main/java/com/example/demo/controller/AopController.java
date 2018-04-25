package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-04-25 17:41
 **/
@Controller
@RequestMapping(value = "/aop")
public class AopController {
    @RequestMapping("/testAop")
    @ResponseBody
    //@MyInfoAnnotation()
    public String testAopController(){
        System.err.println("aa");
        return "吧啦啦小魔仙变身啦！";

    }
}
