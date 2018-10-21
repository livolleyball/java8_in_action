package com.lihm.java8.chapter4;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


// By Stream
public class SimpleStream1 {
    public static void main(String[] args) {

        // create a dish list
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

        List<String> getDishNameByStream = getDishNameByStream(menu);
        System.out.println(getDishNameByStream);

        // 只能遍历一次 只能消费一次
        List<String> tile = Arrays.asList("Java","In","Action");
        Stream<String> s=tile.stream();
        s.forEach(System.out::println);   // foreach 是一个返回 void 的终端操作
//        s.forEach(System.out::println);

        List<String> names =
                menu.stream()
                .filter(d->{
                    System.out.println("filtering " + d.getName());
                    return d.getCalories()>300;
                })
                .map(d->{
                    System.out.println("mapping "+d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(names);

    }


    private static List<String> getDishNameByStream(List<Dish> menu){
        return menu.parallelStream().filter(d->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .limit(1)
                .collect(toList());
    }
}
