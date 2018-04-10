package com.example.demo.entity;

/**
 * @program: 菜单实体
 * @description: ${description}
 * @author: malili
 * @create: 2018-03-15 16:16
 **/
public class DishEntity {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    public enum Type { MEAT, FISH, OTHER}

    public DishEntity(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }
}
