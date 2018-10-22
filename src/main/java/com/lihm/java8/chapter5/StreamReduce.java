package com.lihm.java8.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduce {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        int sum =0;
        for (int x:numbers){
            sum+=x;
        }
        System.out.println(sum);

        System.out.println("==== reduce 相加 ====");
        int sum1=numbers.stream().reduce(0,(a,b)->a+b);
        System.out.println(sum1);

        // Integer类的静态方法 sum
        int sum2=numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum2);

        System.out.println("==== reduce 相乘 ====");
        int product=numbers.stream().reduce(1,(a,b)->a*b);
        System.out.println(product);

        System.out.println("==== reduce 无初始值 相加 ====");  //返回一个 Optional 对象
        Optional<Integer> sum3=numbers.stream().reduce((a, b)->a+b);
        System.out.println(sum3.toString());

        System.out.println("==== reduce 最大值 ====");
        Optional<Integer> max=numbers.stream().reduce(Integer::max);
//        System.out.println(max.isPresent());
        Integer a = Optional.ofNullable(max)
                .map(d->d.get())  //Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
                .orElse(0);
        System.out.println(a);
    }
}
