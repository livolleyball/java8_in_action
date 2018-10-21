package com.lihm.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LambdaUsageConsumer {
    private static void simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer){

        for (Apple a: source){
            consumer.accept(a);
        }
    }

    private static void simpleBiConsumer(String c,List<Apple> source, BiConsumer<Apple,String> consumer){
        for (Apple a: source){
            consumer.accept(a,c);
        }
    }


    public static void main(String[] args){
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("yellow", 110), new Apple("green", 120));
        simpleTestConsumer(list,apple -> System.out.println(apple));
        simpleBiConsumer("XXX",list,(a,s)-> System.out.println(s+a.getColor()+"Weight"+a.getWeight()));
    }
}
