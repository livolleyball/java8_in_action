package com.lihm.java8.chapter5;

import com.lihm.java8.chapter4.Dish;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamNumeric {
    public static void main(String[] args) {
        System.out.println("==== 数值流 ====");
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

        System.out.println("==== 映射数值流 ====");
        int calories =menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);

        System.out.println("==== 转换回对象流 ====");
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> stream=intStream.boxed();  // 将数值流转换为Stream，在将数值范围装箱成为一个一般流时，boxed 尤其有用
        System.out.println(stream);
        stream.forEach(integer -> System.out.println(integer));


        System.out.println("==== 默认值OptionalInt =====");
        OptionalInt maxcalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max=maxcalories.orElse(1);  // 没有最大值的话，显式指定默认最大值
        System.out.println(maxcalories.getAsInt());  //

        System.out.println("==== 数值范围 =====");
        IntStream evenNumbers = IntStream.rangeClosed(1,100)
                .filter(n -> n%2==0);
        System.out.println(evenNumbers.count());

        System.out.println("==== 构建勾股数 ====");
        Stream<int[]> pythagoreanTriples;
        pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                    .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                    .mapToObj(b ->
                            new int[] {a, b, (int) Math.sqrt(a * a + b * b)}));
        pythagoreanTriples.limit(5)
                .forEach(t->System.out.println(t[0]+" , "+t[1]+" , "+t[2]));

        Stream<double[]> pythagoreanTriples2;
        pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                    .mapToObj(b -> new double[]{a,b,Math.sqrt(a*a+b*b)})
                    .filter(t->t[2]%1==0));
        pythagoreanTriples2.limit(5)
                .forEach(t->System.out.println(t[0]+" , "+t[1]+" , "+t[2]));
    }
}
