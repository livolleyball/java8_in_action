package com.lihm.java8.chapter6;


import com.lihm.java8.chapter4.Dish;
//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorGrouping {

    private final static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );


    public static void main(String[] args) {
        System.out.println("==== 分组 ====");
        Map<Dish.Type,List<Dish>> dishesByType =
                menu.stream().collect(Collectors.groupingBy(Dish::getType));
        Optional.ofNullable(dishesByType).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);

        System.out.println("==== 分组计数 ====");
        Optional.ofNullable(menu.stream().collect(groupingBy(Dish::getType,counting())))
                .ifPresent(System.out::println);

        System.out.println("==== 分组平均值 ====");
        Optional.ofNullable(menu.stream().collect(groupingBy(Dish::getType,averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);


        Map<Dish.Type,Double> dishesByTypeDouble =
                menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new ,averagingDouble(Dish::getCalories)));
        Optional.ofNullable(dishesByTypeDouble).ifPresent(System.out::println);

        System.out.println("==== 自定义分组 ====");
        Map<Dish.CaloricLevel,List<Dish>> dishByCaloricLevel = menu.stream().collect(
                groupingBy(dish->{
                    if(dish.getCalories() <=400) return Dish.CaloricLevel.DIET;
                    else if (dish.getCalories()<=700) return Dish.CaloricLevel.NORMAL;
                    else return Dish.CaloricLevel.FAT;
                })
        );
        Optional.ofNullable(dishByCaloricLevel).ifPresent(System.out::println);


        System.out.println("==== 多级分组 ====");
        Map<Dish.Type,Map<Dish.CaloricLevel,List<Dish>>> dishByTypeCaloricLevel= menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish->{ if(dish.getCalories() <=400) return Dish.CaloricLevel.DIET;
                        else if (dish.getCalories()<=700) return Dish.CaloricLevel.NORMAL;
                        else return Dish.CaloricLevel.FAT;})));
        Optional.ofNullable(dishByTypeCaloricLevel).ifPresent(System.out::println);

        System.out.println("==== 查找每一个子组中热量最高的Dish ====");
        Map<Dish.Type,Dish> mostCaloricByType =
                menu.stream()
                .collect(groupingBy(Dish::getType,  // 分类函数
                        collectingAndThen(
                        maxBy(Comparator.comparingInt(Dish::getCalories)), // 包装后的收集器
                                Optional::get)
                ));
        Optional.ofNullable(mostCaloricByType).ifPresent(System.out::println);

        System.out.println("==== groupingBy 联合使用其他收集器 ====");
        Map<Dish.Type,Set<Dish.CaloricLevel>> caloricLevelByType =
                menu.stream().collect(
                        groupingBy(Dish::getType,mapping(
                                dish->{if (dish.getCalories()<=400) return Dish.CaloricLevel.DIET;
                                    else if (dish.getCalories()<=700) return Dish.CaloricLevel.NORMAL;
                                    else return Dish.CaloricLevel.FAT;
                                },toSet()
                        ))
                );
        Optional.ofNullable(caloricLevelByType).ifPresent(System.out::println);
    }
}
