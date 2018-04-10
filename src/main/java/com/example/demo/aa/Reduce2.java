package com.example.demo.aa;

import com.example.demo.entity.Trader;
import com.example.demo.entity.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program:    reduce的练习
 * @description:
 * @author: malili
 * @create: 2018-04-09 15:55
 **/
public class Reduce2 {

    public static final Trader raoul = new Trader("Raoul", "Cambridge");
    public static final Trader mario = new Trader("Mario","Milan");
    public static final Trader alan = new Trader("Alan","Cambridge");
    public static final Trader brian = new Trader("Brian","Cambridge");


    /** 
     * @Description: 1、找出2011年发生的所有交易，并按交易额排序（从低到高)
     * @Param:  
     * @return:  
     * @Author: malili 
     * @Date: 2018/4/9 
    */ 
    public static void transactionByYear(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        transactions = transactions.stream().
                filter(transaction -> transaction.getYear() == 2011).
                sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.err.println("2011年发生的所有交易，并按交易额排序（从低到高):transactions:"+transactions);
        System.err.println("2011年发生的所有交易，并按交易额排序（从低到高):transactions.size:"+transactions.size());

    }

    /**
     * @Description:   2、交易员都在哪些不同的城市工作过
     *
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void findCities(){

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        List<String> cities = transactions.stream().
                map(Transaction::getTrader).distinct().map(trader -> trader.getCity()).distinct().collect(Collectors.toList());
        List<String> cities1 = Arrays.asList(raoul,mario,alan,brian).stream().
                map(trader -> trader.getCity()).distinct().collect(Collectors.toList());
        System.err.println("交易员都在哪些不同的城市工作过:cities:"+cities);
        System.err.println("交易员都在哪些不同的城市工作过:cities1:"+cities1);
    }

    /**
     * @Description: 3、查找所有来自于剑桥的交易员，并按姓名排序
     *
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void findTraders(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        List<Trader> traders = transactions.stream().
                map(Transaction::getTrader).filter(trader -> "Cambridge".equals(trader.getCity())).
                distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        List<Trader> traders1 = Arrays.asList(raoul,mario,alan,brian).stream().
                filter(trader -> "Cambridge".equals(trader.getCity())).
                sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.err.println("查找所有来自于剑桥的交易员，并按姓名排序:traders:"+traders);
        System.err.println("查找所有来自于剑桥的交易员，并按姓名排序:traders1:"+traders1);
    }

    /**
     * @Description:  4、返回所有交易员的姓名字符串，按字母顺序排序
     *                 明天来订正
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void allNameStr(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        List<String> nameStr = transactions.stream().
                map(transaction -> transaction.getTrader().getName().split("")).flatMap(Arrays::stream).
                distinct().sorted().collect(Collectors.toList());
        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining());
        List<String> nameStr1 = Arrays.asList(raoul,mario,alan,brian).stream().
                map(Trader::getName).map(s -> s.split("")).flatMap(Arrays::stream).sorted().distinct().collect(Collectors.toList());
        System.err.println("交易员的姓名字符串，按字母顺序排序:nameStr:"+nameStr);
        System.err.println("交易员的姓名字符串，按字母顺序排序:nameStr1:"+nameStr1);
        System.err.println("交易员的姓名字符串，按字母顺序排序:traderStr:"+traderStr);
    }

    /**
     * @Description:  5、有没有交易员是在米兰工作的
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void isMilan(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        Boolean flag = transactions.stream().anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        Boolean flag1 = Arrays.asList(raoul,mario,alan,brian).stream().anyMatch(trader -> "Milan".equals(trader.getCity()));
        if (flag){
            System.err.println("有交易员是在米兰工作的");
        }else {
            System.err.println("没有交易员是在米兰工作的");
        }
        if (flag1){
            System.err.println("有交易员是在米兰工作的");
        }else {
            System.err.println("没有交易员是在米兰工作的");
        }

    }

    /**
     * @Description:  6、打印生活在剑桥的交易员的所有交易额2650
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void CambridgeValue(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        System.err.println("交易记录："+transactions.size());
        Integer sum = transactions.stream().
                filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).
                map(Transaction::getValue).reduce(0,Integer::sum);
        transactions.stream().
                filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).
                map(Transaction::getValue).forEach(System.out::println);
        System.err.println("打印生活在剑桥的交易员的所有交易额为："+sum);
    }

    /**
     * @Description:  7、所有交易中，最高的交易额是多少
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void maxTransactionValue(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.err.println("所有交易中，最高的交易额是:"+max);
    }
    /**
     * @Description:  8、找到交易额最小的交易
     * @Param:
     * @return:
     * @Author: malili
     * @Date: 2018/4/9
    */
    public static void minTransactionValue(){
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //Optional<Integer> min =
        Integer min = transactions.stream().map(transaction1 -> transaction1.getValue()).reduce(Integer::min).get();
        Optional<Integer> max = transactions.stream().map(transaction1 -> transaction1.getValue()).reduce(Integer::max);
        if (min.equals(300)){
            System.err.println("哈哈哈");
        }
        System.err.println("最小交易额是："+min);
        System.err.println("最大交易额是："+max);
        List<Transaction> transactionList = transactions.stream().
                filter(transaction -> transactions.stream().map(transaction1 -> transaction1.getValue()).reduce(Integer::min).get() == transaction.getValue()).collect(Collectors.toList());
        //这个很棒 本仙女写的有点复杂
        Optional<Transaction> transactionList1 = transactions.stream().reduce((a,b) -> a.getValue()<b.getValue()?a :b);
        System.err.println("交易额最小的交易是:" + transactionList);
        System.err.println("交易额最小的交易是:" + transactionList1);
    }
    public static void main(String[] args) {
        transactionByYear();
        findCities();
        findTraders();
        allNameStr();
        isMilan();
        CambridgeValue();
        maxTransactionValue();
        minTransactionValue();
    }
}
