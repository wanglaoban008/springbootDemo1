package com.example.demo.aa;

/**
 * 行为参数话
 *
 * @author malili
 * @create 2018-03-09 上午11:49
 **/
public class MeaningOfThis {

    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;

            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    public static void main(String... args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
    //这一行的输 出是什么?

}
