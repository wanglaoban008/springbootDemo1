package com.example.demo.aa;

import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * @program: demo
 * @description: ${description}
 * @author: Mr.Wang
 * @create: 2018-03-15 13:49
 **/
public class Letter {
    public static String addHeader(String text){
        return "From Raoul, Mario and Alan: " + text;
    }
    public static String addFooter(String text){
        return text + " Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }
    Function<String,String> header = Letter::addHeader;
    Function<String,String> footer = header.andThen(Letter::checkSpelling).andThen(Letter::addFooter);

    public double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b-a) / 2.0;
    }
    public void aa(){
        integrate((double x) -> x+10,3,7);
    }
}
