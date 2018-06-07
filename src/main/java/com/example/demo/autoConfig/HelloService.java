package com.example.demo.autoConfig;

import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-04-24 16:04
 **/
@Component
public class HelloService {
    private String msg;
    public String sayHello(){
        return "hello"+msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
