package com.lihm.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class LambdaUsageFunction {
    private static String testFunction (Apple apple, Function<Apple,String>  function){
        return function.apply(apple);
    }

    private static  Apple testBiFunction (String color, Long weight, BiFunction<String,Long,Apple> function){
        return function.apply(color,weight);
    }


    public static void main(String[] args){
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("yellow", 110), new Apple("green", 120));
        String result = testFunction(new Apple("green", 150),apple -> apple.toString());
        System.out.println(result);

        IntFunction<Double> f = i-> i* 100.0d;
        double result1=f.apply(100);
        System.out.println(result1);


        Apple a =testBiFunction("bule", (long) 100,(s, w)->new Apple(s,w));
        System.out.println(a);
    }
}
