package com.example.demo.entity;

/**
 * ${DESCRIPTION}
 *
 * @author malili
 * @create 2018-03-08 下午3:47
 **/
public class AppleEntity {
    private String color;
    //库存
    private String inventory;
    private int weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public AppleEntity(String color, String inventory, int weight) {
        this.color = color;
        this.inventory = inventory;
        this.weight = weight;
    }

    public AppleEntity() {
    }
}
