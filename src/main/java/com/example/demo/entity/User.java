package com.example.demo.entity;

import java.util.function.Function;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-05-15 15:46
 **/
public class User {
    private String name;
    private String color;
    private String id;

    public static String getDistinctKey(User user) {
        Function<User, String> function = user1 -> user1.getName() + user1.getColor();
        return function.apply(user);
    }
    public User(String name, String color, String id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
