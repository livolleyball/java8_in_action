package com.lihm.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

    private static <T> void useConsumer(Consumer<T> consumer,T t){
        consumer.accept(t);
        consumer.accept(t);
    }

    public static void main(String[] args){
//        Consumer<String> consumer =(s) -> System.out.println(s);
//        useConsumer(consumer,"Hello lihm");
        useConsumer(s-> System.out.println(s),"hello lihm");
        useConsumer(System.out::println,"Hello lihm1");

        List<Apple> list = Arrays.asList(new Apple("green",150),
                new Apple("yellow",100),new Apple("green",120));
        System.out.println(list);

        list.sort((a1,a2)-> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);

        list.stream().forEach((a-> System.out.println(a)));

        list.stream().forEach(( System.out::println));

        // 类方法
        BiFunction<String,Integer,Character> f2 = String::charAt;
        Character c= f2.apply("Hello",2);
        System.out.println(c);

        // 类实例的方法
        String string = new String("Hello");
        Function<Integer,Character> f3=string::charAt;
        Character c3= f3.apply(4);
        System.out.println(c3);


        Supplier<String> supplier = String::new;

        String s= supplier.get();
        System.out.println(s);

        BiFunction<String,Long,Apple> appleBiFunction = Apple::new;

        Apple apple = appleBiFunction.apply("red", (long) 100);
        System.out.println(apple);


        // 三个入参 1个返回
        ThreeFunction<String,Long,String,ComplexApple> threeFunction=ComplexApple::new;
        ComplexApple ac= threeFunction.apply("Grey",123L,"Fushi");
        System.out.println(ac);



        List<Apple> list2 = Arrays.asList(new Apple("green",150),
                new Apple("yellow",100),new Apple("green",120));

        System.out.println(list2);
        list2.sort(Comparator.comparing(Apple::getColor));  // 根据颜色排序
        System.out.println(list2);


    }
}

