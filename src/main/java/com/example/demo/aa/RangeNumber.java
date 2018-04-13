package com.example.demo.aa;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program:   数值范围
 * @description:
 * @author: malili
 * @create: 2018-04-10 14:32
 **/
public class RangeNumber {
    /**
     * @Description:  range是不包含结束值的，而rangeClosed则包含结束值
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/10
    */
    public static void range1(){
        //range是不包含结束值的
        IntStream intStream = IntStream.range(1,10).filter(value -> value % 2 == 0);
        //rangeClosed则包含结束值
        IntStream rangeClosed = IntStream.rangeClosed(1,10).filter(value -> value % 2 == 0);
        long count = intStream.count();
        long rangeClosedCount = rangeClosed.count();
        System.err.println("count："+count);
        System.err.println("rangeClosedCount："+rangeClosedCount);
        //intStream.forEach(System.err::println);

    }

    /**
     * @Description:  勾股数流
     *                  这个demo让我又回忆起打印数组 结果为[I@1d56ce6a，所以我们打印数组可以用arrays.toString(aa)
     *                  有时候arrays.toString 打印出来的还是上面那串字符串，你知道是为什么吗，
     *                  那串字符串其实是内存地址，你打印的是内存地址而不是数组的内容也就是值
     *                  所以比如是一个object[],里面可能有int数组和string数组，还可以用Arrays.toString(array)这个方法就能打印数组的元素了
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/10
    */
    public static void sanYuan(){

        //请注意，你在filter之后调用boxed，从rangeClosed返回的IntStream生成一个
        //Stream<Integer>。这是因为你的map会为流中的每个元素返回一个int数组。而IntStream
        //中的map方法只能为流中的每个元素返回另一个int，这可不是你想要的！
        Stream<int[]> aa =IntStream.rangeClosed(1,10).
                filter(b -> Math.sqrt(3*3 + b*b) % 1 == 0).//这里选出可以和a组成勾股数的b
                boxed().
                map(b -> new int[]{3,b,(int)Math.sqrt(3 * 3 + b * b)});
        aa.forEach(ints -> System.err.println("int1:"+ Arrays.toString(ints)));
        //流只能被操作一次，如果想再用会报错
        //aa.forEach(ints -> System.err.println("int1:"+ ints.toString()));

        //你可以用IntStream 的mapToObj方法改写它，这个方法会返回一个对象值流
        Stream<int[]> ob = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(3*3 + b*b) % 1 == 0)
                .mapToObj(b -> new int[]{3, b, (int) Math.sqrt(3 * 3 + b * b)});

        ob.forEach(ints -> System.err.println("ob1:"+Arrays.toString(ints)));

        //看出来了没  IntStream.map 返回的是另一个int，只返回一个int哦，一个值。不能返回数组
        IntStream oc = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(3 * 3 + b * b) % 1 == 0)
                .map(b -> (int) Math.sqrt(3 * 3 + b * b));

        oc.forEach(in-> System.err.println("oc:"+in));

        Stream<int[]> cc =IntStream.rangeClosed(1,10)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a,100)
                        .filter(b -> Math.sqrt(a*a +b*b) % 1 ==0)
                        .mapToObj(b -> new int[]{a,b,(int)Math.sqrt(a*a+b*b)}));

        cc.forEach(ints -> System.err.println("cc:"+Arrays.toString(ints)));

        //IntStream a  =IntStream.rangeClosed(1,10);
        //a.forEach(System.out::println);

        //更加紧凑  相当于先产生三元数，然后再过滤符合 的条件
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})//产生三元数
                                        .filter(t -> t[2] % 1 == 0)); // 三元数组中第三个数必须是整数
    }

    public static void main(String[] args) {
        range1();
        sanYuan();
    }
}
