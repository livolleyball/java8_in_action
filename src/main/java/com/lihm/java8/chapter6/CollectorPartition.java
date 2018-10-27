package com.lihm.java8.chapter6;

import com.lihm.java8.chapter4.Dish;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class CollectorPartition {
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

        System.out.println("==== 分区 ====");
        Map<Boolean, Map<Dish.Type,List<Dish>>> vegetarianDishByType =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)
                        )
                );
        Optional.ofNullable(vegetarianDishByType).ifPresent(System.out::println);

        System.out.println("==== 分区 找到热量最高的菜 ====");
        Map<Boolean, Dish> mostCaloricPartitioneByVegetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get
                                )
                        )
                );
        Optional.ofNullable(mostCaloricPartitioneByVegetarian).ifPresent(System.out::println);

        System.out.println("==== 将数字按质数和非质数分区 ====");
        Optional.ofNullable(partitionPrimes(11)).ifPresent(System.out::println);


    }

    public static boolean isPrime (int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,candidateRoot)
                .noneMatch(i-> candidate % i==0);
    };
    public static Map<Boolean,List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
    };
}
