package com.example.demo.aa;

import com.example.demo.entity.AppleEntity;
import com.example.demo.entity.TransactionEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author malili
 * @create 2018-03-08 下午2:23
 **/
public class JavaLamda {

    public static List<AppleEntity> filterGreenApples(List<AppleEntity> inventory){

        List<AppleEntity> result = new ArrayList<>();
        for (AppleEntity appleEntity : inventory){
            if ("green".equals(appleEntity.getColor())) {
                result.add(appleEntity);
            }
        }
        return result;
    }


    public static boolean isGreenApple(AppleEntity appleEntity) {
        return "green".equals(appleEntity.getColor());
    }
    public static boolean isHeavyApple(AppleEntity appleEntity) {
        return appleEntity.getWeight() > 150;
    }
    public interface Predicate<T>{
        boolean test(T t);
    }


    static List<AppleEntity> filterApples(List<AppleEntity> inventory,
                                          Predicate<AppleEntity> p) {
        List<AppleEntity> result = new ArrayList<>();
        for (AppleEntity appleEntity : inventory) {
            if (p.test(appleEntity)) {
                result.add(appleEntity);
            }
        }
        return result;
    }

    public void aa(){
        List<AppleEntity> inventory= new ArrayList<>();
        //filterApples(inventory, AppleEntity::isGreenApple);

        filterApples(inventory, (AppleEntity a) -> "green".equals(a.getColor()) );

        //filter(inventory, (AppleEntity a) -> a.getWeight() > 150 );
        List<AppleEntity> appleEntities = new ArrayList<>();
        appleEntities.stream().filter(appleEntity -> "green".equals(appleEntity.getColor()));

        //stream
        List<AppleEntity> heavyApples1 =
                inventory.stream().filter((AppleEntity a) -> a.getWeight() > 150)
                        .collect(Collectors.toList());


        // parallelStream
        List<AppleEntity> heavyApples2 =
                inventory.parallelStream().filter((AppleEntity a) -> a.getWeight() > 150) .collect(Collectors.toList());

    }

    public void huobi(){

        Map<Currency, List<TransactionEntity>> transactionsByCurrencies = new HashMap<>();
        List<TransactionEntity> transactionEntities = new ArrayList<>();

        for (TransactionEntity transactionEntity : transactionEntities) {
            if(transactionEntity.getPrice() > 1000){
                Currency currency = transactionEntity.getCurrency();
                List<TransactionEntity> transactionsForCurrency =
                        transactionsByCurrencies.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<TransactionEntity>();
                }else{
                    transactionsByCurrencies.put(currency,
                            transactionsForCurrency);
                }
            }
        }

        List<TransactionEntity> transactionsForCurrencies
                = transactionEntities.stream().filter(transactionEntity -> transactionEntity.getPrice() > 1000)
                .collect(Collectors.toList());
    }

    public interface AppleFormatter{
        String accept(AppleEntity a);
    }

    public class AppleFancyFormatter implements AppleFormatter{
        public String accept(AppleEntity appleEntity){
            String characteristic = appleEntity.getWeight() > 150 ? "heavy" :
                    "light";
            return "A " + characteristic +
                    " " + appleEntity.getColor() +" appleEntity";
        } }
    public class AppleSimpleFormatter implements AppleFormatter{ public String accept(AppleEntity appleEntity){
        return "An appleEntity of " + appleEntity.getWeight() + "g";
    }
    }

    public static void prettyPrintApple(List<AppleEntity> inventory,
                                        AppleFormatter formatter){
        for(AppleEntity appleEntity : inventory){
            String output = formatter.accept(appleEntity);
            System.out.println(output);
        } }

    public void prettyPrintApple(){
        List<AppleEntity> inventory = new ArrayList<>();
        prettyPrintApple(inventory, new AppleFancyFormatter());
    }


    public void sortApple(){
        List<AppleEntity> inventory = new ArrayList<>();
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
        List<AppleEntity> appleEntities = new ArrayList<>();
        appleEntities.sort(Comparator.comparing(AppleEntity::getWeight).reversed().thenComparing(AppleEntity::getColor));
        Predicate<AppleEntity> applelist = appleEntity -> appleEntity.getWeight()>150;
        //applelist.
       

    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
    
    /** 
    * @Description: 比较器复合
    * @Param:  
    * @return:  
    * @Author: malili 
    * @Date: 2018/3/14 
    */ 
    public void compareBox(){
        //升序排序
        Comparator.comparing(AppleEntity::getWeight);
        //降序排序
        Comparator.comparing(AppleEntity::getWeight).reversed();
        //重量相同比较颜色
        Comparator.comparing(AppleEntity::getWeight).reversed().thenComparing(AppleEntity::getColor);
    }

    /** 
    * @Description: 谓词复合
    * @Param:  
    * @return:  
    * @Author: malili 
    * @Date: 2018/3/14 
    */ 
    public void verbBox(){
        AppleEntity appleEntity = new AppleEntity();
        List<AppleEntity> appleEntities = new ArrayList<>();
        appleEntities.stream().filter(appleEntity1 -> appleEntity1.getWeight()>10);
    }

    public void functionBox(){
        Function<Integer,Integer> f = x ->x+1;
        Function<Integer,Integer> g = x ->x+1;
    }





}
