package com.lihm.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

public class LambdaUsagePredicate {
    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a)) result.add(a);
        }
        return result;
    }


    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getWeight())) result.add(a);
        }
        return result;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String,Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getColor(),a.getWeight())) result.add(a);
        }
        return result;
    }
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("yellow", 110), new Apple("green", 120));
        List<Apple> greenList = filter(list, (apple) -> apple.getColor().equals("green"));
        System.out.println(greenList);

        List<Apple> weightList = filterByWeight(list, w -> w > 130);
        System.out.println(weightList);

        List<Apple> BiList = filterByBiPredicate(list,(color,weight) -> color.equals("yellow")&&weight>110);
        System.out.println(BiList);

    }
}
