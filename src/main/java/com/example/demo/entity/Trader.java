package com.example.demo.entity;

/**
 * @program:  贸易商
 * @description:
 * @author: malili
 * @create: 2018-04-09 15:50
 **/
public class Trader {
    //姓名
    private final String name;
    //城市
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
