package com.lihm.java8.chapter5;

import com.lihm.java8.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamFindMatch {
    public static void main(String[] args) {
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
        System.out.println("==== 检查谓词是否至少匹配一个元素   ====");
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly");
        }

        System.out.println("===== 检查谓词是否匹配所有元素 ====");
        boolean isHealth = menu.stream()
                .allMatch(d-> d.getCalories()<1000);
        System.out.println(isHealth);

        boolean isHealth2 = menu.stream()
                .noneMatch(d-> d.getCalories()>=1000);
        System.out.println(isHealth2);

        System.out.println("==== 查找元素 ====");
        Optional<Dish> dish=
            menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));

        System.out.println("==== 查找第一个元素 ====");
        List<Integer> someNums = Arrays.asList(1,2,3,4,5,6);
        Optional<Integer> fristItem =
                someNums.stream()
//                .map(x->x*x)
                .filter(x->(x*x)%3==0)
                .findFirst();
        fristItem.ifPresent(x ->System.out.println(x));
    }
}
