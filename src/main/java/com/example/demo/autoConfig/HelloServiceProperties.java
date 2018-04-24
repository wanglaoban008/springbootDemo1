package com.example.demo.autoConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program:
 * @description:   写个简单的spring-boot自动配置demo ——属性
 * @author: malili
 * @create: 2018-04-24 15:58
 **/
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {

    private static final String MSG =" tianxia";
    private String msg =MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
