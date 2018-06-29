package com.example.demo;

import com.example.demo.test.common.upload.CustomMultipartResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-06-29 18:31
 **/
/*
 * 将 spring 默认的文件上传处理类取消自动配置，这一步很重要，没有这一步，当multipartResolver重新指向了我们定义好
 * 的新的文件上传处理类后，前台传回的 file 文件在后台获取会是空，加上这句话就好了，推测不加这句话，spring 依然
 * 会先走默认的文件处理流程并修改request对象，再执行我们定义的文件处理类。（这只是个人推测）
 * exclude表示自动配置时不包括Multipart配置
 */
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})

@Configuration
@ComponentScan(basePackages = {"com.example"})
@ServletComponentScan(basePackages = {"com.example"})
public class UploadProgressApplication {

    /*
     * 将 multipartResolver 指向我们刚刚创建好的继承 CommonsMultipartResolver 类的自定义文件上传处理类
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CustomMultipartResolver customMultipartResolver = new CustomMultipartResolver();
        return customMultipartResolver;
    }

    public static void main(String[] args) {
        SpringApplication.run(UploadProgressApplication.class, args);
    }
}
