package com.lihm.java8.chapter6;

import com.lihm.java8.Apple;
import com.lihm.java8.chapter4.Dish;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorIntroduce {
    private final static  List<Dish> menu = Arrays.asList(
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
        List<Apple> list = Arrays.asList(new Apple("green",150),
                new Apple("yellow",100),new Apple("green",120));



        List<Apple> greenList =list.stream().filter(a->a.getColor().equals("green")).collect(Collectors.toList());
        Optional.ofNullable(greenList).ifPresent(System.out::println);

        System.out.println("==== 按颜色分组 ====");
        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);


        System.out.println("==== 通过收集器按颜色分组 =====");
        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);

        System.out.println("==== 收集器高级归约 =====");
        List<Apple> apples=
                list.stream().collect(Collectors.toList());
        System.out.println(apples.size());

        System.out.println("==== 归约和汇总 ====");
        long howManyDish = list.stream().count();
        System.out.println(howManyDish);

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);

        System.out.println("==== 查找流中的最大值和最小值 ====");
        Optional<Dish> mostCaloriesDish =
                menu.stream()
                .collect(maxBy(dishCaloriesComparator));

        Optional<Dish> minCaloriesDish =
                menu.stream()
                        .collect(minBy(dishCaloriesComparator));
        System.out.println(mostCaloriesDish.get().getCalories());
        System.out.println(minCaloriesDish.get().getCalories());

        System.out.println("==== 汇总 =====");
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        System.out.println("==== 平均值 =====");
        double avgCalories =menu.stream().collect(averagingDouble(Dish::getCalories));
        System.out.println(avgCalories);


        System.out.println("==== 元素计数 =====");
        Optional.of(menu.stream().collect(counting())).ifPresent(System.out::println);

        System.out.println("==== 元素统计 =====");
        IntSummaryStatistics cntDish =menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(cntDish);

        System.out.println("==== 连接字符串 =====");
        String shortMenu =menu.stream().map(Dish::getName).collect(joining());
//        String shortCollectMenu =
//                menu.stream().collect(joining());
        String shortMenuNew =menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);
        System.out.println(shortMenuNew);

        System.out.println("==== 广义的归约汇总 =====");
        int totalCalories1 = menu.stream().collect(reducing(
                0,Dish::getCalories,(i,j)->i+j)
        );
        System.out.println(totalCalories1);

        Optional<Dish> mostCaloriesDish1 = menu.stream().collect(reducing(
                (d1,d2)->d1.getCalories() > d2.getCalories() ?d1:d2
        ));
        System.out.println(mostCaloriesDish1.get().getCalories());



    }

    private static Map<String,List<Apple>> groupByNormal(List<Apple> apples){
        Map<String,List<Apple>> map = new HashMap<>();
        for (Apple a:apples){
            List<Apple> list = map.get(a.getColor());
            if(null == list){
                list = new ArrayList<>();
                map.put(a.getColor(),list);
            };
            list.add(a);
        }
        return map;
    }
    private static Map<String,List<Apple>> groupByCollector(List<Apple> apples){
        return apples.stream().collect(groupingBy(Apple::getColor));
    }

}
