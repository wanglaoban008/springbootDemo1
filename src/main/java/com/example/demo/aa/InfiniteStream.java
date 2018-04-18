package com.example.demo.aa;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program:  创建无限流
 * @description:
 * @author: malili
 * @create: 2018-04-16 15:13
 **/
public class InfiniteStream {

    /**
     * @Description:   创建无限流
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/16
    */
    public static void createInfiniteStream(){
        Stream.iterate(0,n ->n+3).limit(5).forEach(System.err::println);
        List<Integer> aa = Stream.iterate(0, n ->n+3).limit(5).collect(Collectors.toList());
        System.err.println("aa:"+aa);
    }

    /** 
     * @Description: 斐波那契 元组序列  Stream.iterate依次生成
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/16 
    */ 
    public static void feiBoNaQi(){
        List<Integer> ints = Stream
                .iterate(new int[]{0,1},t -> new int[]{t[1],t[0]+t[1]}).limit(10).map(t ->t[0]).collect(Collectors.toList());
        System.err.println("ints:"+ints);
    }

    /** 
     * @Description:   按需生成一个无限流 
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/16 
    */ 
    public static void generate1(){
        List<Double> doubleList = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
        System.err.println("doubleList"+doubleList);
    }

    public static void generate2(){
        List<Double> doubleList = DoubleStream     //数值流
                .generate(Math::random).limit(5)  // 生成随机五个数
                .boxed()                          // 将数值流转一般流
                .collect(Collectors.toList());    //转集合

        System.err.println("数值流doubleList："+doubleList);

        //生成一个全是1的无限流(这里啊一定要加limit不然会报错)
        List<Integer> ints11 = IntStream.generate(()->1).boxed().limit(10).collect(Collectors.toList());
        System.err.println("ints11："+ints11);

        //但这里使用的匿名类和Lambda的区别在于，匿名类可以通过字段定义状态，而状态又可以用
        //getAsInt方法来修改。这是一个副作用的例子。你迄今见过的所有Lambda都是没有副作用的；
        //它们没有改变任何状态。
        //这个方法本仙女不会用，尽量不要调用一直在跑不知道为啥特别消耗cpu这是我猜的
        List<Integer> twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).boxed().limit(10).collect(Collectors.toList());//这里必须用limit不然会一直有无限个2

        System.err.println("twos:"+twos);
    }

    /** 
     * @Description:       你现在需要做的是建立一个IntSupplier，它要把前一项的
     *                       值保存在状态中，以便getAsInt用它来计算下一项。此外，在下一次调用它的时候，还要更新
     *                       IntSupplier的状态
     *                       这个方法不要调用，巨卡就算用了limit感觉没什么卵用，好像打印输出显示，但是一直在调用方法不清楚为什么
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/16 
    */ 
    public static void generate3(){
        //IntSupplier fib = new IntSupplier(){
        //    private int previous = 0;
        //    private int current = 1;
        //    public int getAsInt(){
        //        int oldPrevious = this.previous;
        //        int nextValue = this.previous + this.current;
        //        this.previous = this.current;
        //        this.current = nextValue;
        //        return oldPrevious;
        //    }
        //};
        //IntStream.generate(fib).limit(10).forEach(System.out::println);

        IntSupplier intSup = new IntSupplier() {
            private int lastValue = 1;
            private int value = 2;
            @Override
            public int getAsInt() {
                int oldValue = lastValue;
                int nextValue = lastValue+value;
                lastValue = value;
                value = nextValue;
                return oldValue;
            }
        };

        //请注意，因为你处理的是一个无限流，所以必须使用limit操作来显
        //式限制它的大小；否则，终端操作（这里是forEach）将永远计算下去。同样，你不能对无限流
        //做排序或归约，因为所有元素都需要处理，而这永远也完不成！
        List<Integer> integers = IntStream
                .generate(intSup)
                .boxed()
                .limit(5)
                .collect(Collectors.toList());
        System.err.println("integers:"+integers);
    }

    public static void main(String[] args) {
        createInfiniteStream();
        feiBoNaQi();
        generate1();
        generate2();
        generate3();
    }

}
