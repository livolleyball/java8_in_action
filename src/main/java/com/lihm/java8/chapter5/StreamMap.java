package com.lihm.java8.chapter5;

import com.lihm.java8.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMap {
    public static void main(String[] args){
        System.out.println("==== 使用谓词筛选 ====");
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );


        System.out.println("==== 对流中的每一个元素应用函数 ====");
        List<String> dashName = menu.stream()
                .filter(Dish::isVegetarian)
                .map(d->{return d.getName();})
                .collect(Collectors.toList());
        System.out.println(dashName);

        List<Integer> dashNameLength = menu.stream()
                .filter(Dish::isVegetarian)
                .map(d->{return d.getName();})
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dashNameLength);

        System.out.println("==== 流的扁平化 平铺  ====");
        List<String> words =Arrays.asList("hello ","world");
        List<String>  wordItem = words.stream()
                .map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(wordItem);


        List<String>  uniqueChar = words.stream()
                .flatMap(word->Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueChar);



    }

}
