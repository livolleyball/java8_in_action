package com.lihm.java8;

public class LambdaUsage {

    @FunctionalInterface
    public interface Adder{
        int add(int a ,int b);
    }

    @FunctionalInterface
    public interface NewAdder extends Adder {
        int add(int a ,int b);
    }

    public static void main (String[] args){
        Runnable r1 =()->System.out.println("hello r1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello r2");
            }
        };
        process(r1);
        process(r2);
        process(() -> System.out.println("hello r3"));
    }

    public static void process(Runnable r){
        r.run();
    }
}
