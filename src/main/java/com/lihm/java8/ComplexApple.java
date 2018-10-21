package com.lihm.java8;

public class ComplexApple {

    private String color;
    private long weigth;
    private String name;

    public ComplexApple(String color, long weigth, String name) {
        this.color = color;
        this.weigth = weigth;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getWeigth() {
        return weigth;
    }

    public void setWeigth(long weigth) {
        this.weigth = weigth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComplexApple{" + "color='" + color + '\'' + ", weigth=" + weigth + ", name='" + name + '\'' + '}';
    }
}
