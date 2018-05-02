package com.example.demo.lock;


/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-04-27 16:31
 **/
public class SynchronizeMll implements Runnable {

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 5; i++) {
                System.err.println("线程名:"+Thread.currentThread().getName()+" "+i);
            }
        }

        for (int i = 0; i < 5 ; i++) {
            System.err.println("线程名:"+Thread.currentThread().getName()+" "+i);
        }
    }

    //当线程访问synchronized修饰的代码块，另一个线程必须等当前执行完才能执行改代码
    //当一个线程访问synchronized修饰的代码块，其他线程可以访问非synchronized代码
  public static void main(String[] args) {
        SynchronizeMll threadMll = new SynchronizeMll();
        Thread thA = new Thread(threadMll,"thA");
        Thread thB = new Thread(threadMll,"thB");
        thA.start();
        thB.start();
    }
}
