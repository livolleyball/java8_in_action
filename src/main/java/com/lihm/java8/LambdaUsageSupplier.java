package com.lihm.java8;

import java.util.function.Supplier;

public class LambdaUsageSupplier {

    private  static Apple CreateApple(Supplier<Apple> supplier){
        return supplier.get();

    }

    public static void main(String[] args) {

//        Supplier<String> s = String::new;  // method inference
//        System.out.println(s.get().getClass());

        Apple a2= CreateApple(() -> new Apple("Green",100));
        System.out.println(a2);

    }
}
