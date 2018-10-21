package com.lihm.java8.chapter5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {
    private static Stream<String> CreateSteamFromCollection() {
        List<String> list = Arrays.asList("hello", "lihm", "java", "world");
        return list.stream();
    }

    private static Stream<Integer> CreateSteamFromIterrate() {
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2);
        return stream;
    }

    private static Stream<Obj> CreateObjStreamFromGenerate(){
        return Stream.generate(new ObjSupplier());

    }

    static class ObjSupplier implements Supplier<Obj>{
        private int index =0;
        private Random random = new Random(System.currentTimeMillis());


        @Override
        public Obj get() {
            index=random.nextInt(100);
            return new Obj(index,"Name"+index);
        }
    }

    static class Obj{
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }

    private static Stream<Double> CreateSteamFromGenerate() {
        Stream<Double> stream = Stream.generate(Math::random);  // 静态方法作为新值生成器
        return stream;
    }

    public static void main(String[] args) {

        CreateSteamFromCollection().forEach(System.out::println);

        System.out.println("==== 由值创建流 ====");
        Stream<String> stream = Stream.of("hello", "lihm", "java", "world");
        stream.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("==== 由数组创建流 ====");
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        System.out.println("==== 由文件创建流 ====");
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("/Users/lihm/Desktop/learn/learn_java/java8_in_action/src/main/java/com/lihm/java8/chapter5/data.txt"))) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(uniqueWords);

        System.out.println("==== 由 函数 迭代 创建流 ====");
        CreateSteamFromIterrate().limit(10).forEach(System.out::println);

        System.out.println("==== 由 函数 生成 创建流 ====");
        CreateSteamFromGenerate().limit(10).forEach(System.out::println);

        System.out.println("==== 由 对象Supplier 创建流 ====");
        CreateObjStreamFromGenerate().limit(10).forEach(System.out::println);

    }
}
