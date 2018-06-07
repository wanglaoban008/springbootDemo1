package com.example.demo.test.SingleTonMode;

/**
 * @program:
 * @description:  饿汉模式
 *
 * 线程安全，调用效率高，不能延迟加载
 *
 * @author: malili
 * @create: 2018-06-07 17:18
 **/
public class ImageLoader {
    private ImageLoader() {
    }
    private static ImageLoader instance = new ImageLoader();
    public static ImageLoader getInstance(){
        return instance;
    }
}
