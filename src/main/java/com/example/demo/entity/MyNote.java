package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-06-13 16:09
 **/
public class MyNote implements Serializable {
    private static String id = "3";
    transient private String name;
    private String content;
    private Date day;
    private String weather;
    static {
        String author = "malili";
    }

    public MyNote(String name, String content, Date day, String weather) {
        this.name = name;
        this.content = content;
        this.day = day;
        this.weather = weather;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "MyNote{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", day=" + day +
                ", weather='" + weather + '\'' +
                '}';
    }
}
