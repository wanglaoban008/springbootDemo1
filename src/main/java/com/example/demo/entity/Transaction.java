package com.example.demo.entity;

/**
 * @program:   交易
 * @description:
 * @author: malili
 * @create: 2018-04-09 15:53
 **/
public class Transaction {
    //贸易商
    private final Trader trader;
    //年
    private final int year;
    //值
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
