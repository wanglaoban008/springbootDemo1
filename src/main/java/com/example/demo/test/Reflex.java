package com.example.demo.test;

/**
 * @program:
 * @description:  四种反射方式获取class
 * @author: malili
 * @create: 2018-06-06 15:36
 **/
public class Reflex {

    public void getClassAa(){

    }

    /** 
     * @Description: 四种反射方法，比较classLoader和forName()
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/6/6 
    */ 
    public static void main(String[] args) {
        //这里初始化这个类
        //GoZao go = new GoZao();
        //通过属性.getClass
        //System.err.println("goZao:"+go.getClass());
        //通过类.class
        //System.err.println("goZao2:"+GoZao.class);
        try {
            //这里初始化了静态方法
            Class goZao3 = Class.forName("com.example.demo.test.GoZao");
            //这里测下forName和classLoader 的区别
            System.err.println("goZao3:"+goZao3);
            //这里初始化了普通方法
            goZao3.newInstance();

            System.err.println("====================================================");

            ClassLoader classLoader = Reflex.class.getClassLoader();
            //这里只将类加载到jvm没有初始化
            Class goZao4 = classLoader.loadClass("com.example.demo.test.GoZao");
            System.err.println("goZao4:"+goZao4);
            //这里初始化了静态方法和普通方法
            goZao4.newInstance();

        }catch (Exception e){

        }


    }
}
