package com.example.demo.entity;

import java.util.Currency;

/**
 * ${DESCRIPTION}
 *
 * @author malili
 * @create 2018-03-08 下午4:38
 **/
public class TransactionEntity {

    private int price;
    private Currency currency;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
