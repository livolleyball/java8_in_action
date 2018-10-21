package com.lihm.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {
//函数式接口，接口的一种，接口里面有且只有一个抽象方法，default方法 / 静态方法 除外
    @FunctionalInterface   // 标注为函数式接口
    public interface AppleFilter {
        boolean filter(Apple apple);

        default void print(String var){
            System.out.println(var);
        }
    }

    public static List<Apple> findApple(List<Apple> apples,AppleFilter appleFilter){
        List<Apple> list = new ArrayList<Apple>();

        for (Apple apple: apples){
            if (appleFilter.filter(apple))
                list.add(apple);
        }
        return list;
    }

    public static class   GreenAnd150WeightFilter implements AppleFilter{

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green")&&
                    apple.getWeight()>=150);
        }

    };

    public static class   Yellowless150WeightFilter implements AppleFilter{

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("yellow")&&
                    apple.getWeight()<=150);
        }

    };

    public static List<Apple> findGreenApple(List<Apple > apples){
        List<Apple> list = new ArrayList<Apple>();

        for(Apple apple:apples){
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }
    public static List<Apple> findApple(List<Apple> apples,String color){
        List<Apple> list = new ArrayList<Apple>();

        for(Apple apple:apples){
            if(color.equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(new Apple("green",150),
                new Apple("yellow",100),new Apple("green",120));
//        List<Apple> greenApples = findGreenApple(list);
//        assert greenApples.size()==2;

//        List<Apple> greenApples = findApple(list,"green");
//        List<Apple> yellowApples = findApple(list,"yellow");
//
//        System.out.println(greenApples);
//        System.out.println(yellowApples);

//       List<Apple> result = findApple(list,new GreenAnd150WeightFilter());
//        System.out.println(result);
//
//        List<Apple> yellowresult = findApple(list, new AppleFilter() {
//            @Override
//            public boolean filter(Apple apple) {
//                return "yellow".equals(apple.getColor());
//            }
//        });
//        System.out.println(result);
//        System.out.println(yellowresult);

        List<Apple> lamdaResult = findApple(list ,(Apple apple) -> {
            return apple.getColor().equals("green");
        });

        System.out.println(lamdaResult);

// 线程 旧版
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });

        thread.start();

// 线程 lamda
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();


        Thread.currentThread().join();
}
}
