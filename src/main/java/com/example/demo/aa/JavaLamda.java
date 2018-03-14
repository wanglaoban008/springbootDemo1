package com.example.demo.aa;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author malili
 * @create 2018-03-08 下午2:23
 **/
public class JavaLamda {

    public static List<Apple> filterGreenApples(List<Apple> inventory){

        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }


    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
    public interface Predicate<T>{
        boolean test(T t);
    }


    static List<Apple> filterApples(List<Apple> inventory,
                                    Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public void aa(){
        List<Apple> inventory= new ArrayList<>();
        //filterApples(inventory, Apple::isGreenApple);

        filterApples(inventory, (Apple a) -> "green".equals(a.getColor()) );

        //filter(inventory, (Apple a) -> a.getWeight() > 150 );
        List<Apple> apples = new ArrayList<>();
        apples.stream().filter(apple -> "green".equals(apple.getColor()));

        //stream
        List<Apple> heavyApples1 =
                inventory.stream().filter((Apple a) -> a.getWeight() > 150)
                        .collect(Collectors.toList());


        // parallelStream
        List<Apple> heavyApples2 =
                inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150) .collect(Collectors.toList());

    }

    public void huobi(){

        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if(transaction.getPrice() > 1000){
                Currency currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency =
                        transactionsByCurrencies.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<Transaction>();
                }else{
                    transactionsByCurrencies.put(currency,
                            transactionsForCurrency);
                }
            }
        }

        List<Transaction> transactionsForCurrencys
                = transactions.stream().filter(transaction -> transaction.getPrice() > 1000)
                .collect(Collectors.toList());
    }

    public interface AppleFormatter{
        String accept(Apple a);
    }

    public class AppleFancyFormatter implements AppleFormatter{
        public String accept(Apple apple){
            String characteristic = apple.getWeight() > 150 ? "heavy" :
                    "light";
            return "A " + characteristic +
                    " " + apple.getColor() +" apple";
        } }
    public class AppleSimpleFormatter implements AppleFormatter{ public String accept(Apple apple){
        return "An apple of " + apple.getWeight() + "g";
    }
    }

    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormatter formatter){
        for(Apple apple: inventory){
            String output = formatter.accept(apple);
            System.out.println(output);
        } }

    public void prettyPrintApple(){
        List<Apple> inventory = new ArrayList<>();
        prettyPrintApple(inventory, new AppleFancyFormatter());
    }


    public void sortApple(){
        List<Apple> inventory = new ArrayList<>();
        prettyPrintApple(inventory, new AppleFancyFormatter());
        Thread thread = new Thread(() -> System.err.println("aa"));

    }

    //@FunctionalInterface
    public interface aa{
        void aa();
    }

    public static String processFile() throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }

    }

    public void cc(){
        forEach(Arrays.asList(1,2,3,4),(Integer i) ->{System.err.println(i);}

        );
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
            for (T t:list){
                c.accept(t);
            }
    }

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public static <T, R> List<R> map(List<T> list,
                                     Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    // [7, 2, 6]
    List<Integer> l = map(
            Arrays.asList("lambdas", "in", "action"),
            (String s) -> s.length()
    );

    public static void main(String[] args) {
        List<Integer> l = map(
                Arrays.asList("lambdas", "in", "action"),
                (String s) -> {
                    System.err.println(s.length());return s.length();}
        );

        forEach(Arrays.asList("aa","bb","cc"),System.out::print);
        List<Integer> ints = map(Arrays.asList("1","2","3"),Integer::parseInt);
        forEach(ints,System.out::print);
        List<Apple> apples = new ArrayList<>();
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        Predicate<Apple> applelist = apple -> apple.getWeight()>150;
        //applelist.

    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }





}
