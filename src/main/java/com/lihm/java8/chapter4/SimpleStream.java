package com.lihm.java8.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// BY collections
public class SimpleStream {
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

        List<String> dishNameByCollections = getDishNamesByCollections(menu);
        System.out.println(dishNameByCollections);

    }

    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();

        // filter and get calories less 400
        for(Dish d:menu){
            if(d.getCalories()<400)
                lowCalories.add(d);
        }

        // sort
        Collections.sort(lowCalories,(d1,d2)->Integer.compare(d1.getCalories(),d2.getCalories()));

        List<String> dishNameList = new ArrayList<>();

        for (Dish d:lowCalories){
            dishNameList.add(d.getName());
        }
        return dishNameList;
    }

}
