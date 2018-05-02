package com.example.demo.lock;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-04-27 17:10
 **/
public class Synchronize2 {

    public void fun1(){
        synchronized (this){
            for (int i = 0; i < 5; i++) {
                System.err.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }
    public void fun2(){
        synchronized (this){
            for (int i = 10; i > 5; i--) {
                System.err.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }

    public static void main(String[] args) {
        Synchronize2 synchronize2 = new Synchronize2();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronize2.fun1();
            }
        },"thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronize2.fun2();
            }
        },"thread2");

        thread1.start();
        thread2.start();
    }



}
