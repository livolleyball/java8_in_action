package com.lihm.java8.chapter5;

import com.lihm.java8.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {
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
        List<String> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .map(d->{return d.getName();})
                .collect(Collectors.toList());
        System.out.println(vegetarianMenu);

        System.out.println("==== 筛选各异的元素 ====");
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,3,4,1,2);
        numbers.stream()
                .filter(integer -> integer%2==0)
                .distinct()
                .forEach(System.out::println);


        System.out.println("==== 截短流 ====");
        List<Dish> dishes = menu.stream()
                .filter(Dish::isVegetarian)
                .limit(3) // 不超过给定长度的流
                .collect(Collectors.toList());
        System.out.println(dishes);

        System.out.println("==== 跳过元素 ====");
        List<Integer> numbers2 = Arrays.asList(1,2,3,4,8,3,4,1,2);
        numbers2.stream()
                .filter(integer -> integer%2==0)
                .skip(2)  // 返回 一个扔掉前n个元素 的流
                .forEach(System.out::println);

    }

}
