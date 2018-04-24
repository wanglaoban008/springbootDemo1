package com.example.demo.autoConfig;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-04-24 16:04
 **/
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
