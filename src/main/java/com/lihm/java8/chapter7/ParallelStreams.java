package com.lihm.java8.chapter7;

import java.util.function.Function;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void main(String[] args) {
        System.out.println("==== 顺序流转化为并行流 =====");
        long sumR= parallelSum(10);
        System.out.println(sumR);

        System.out.println("顺序归约: " + measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");

        System.out.println("迭代归约: " + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");

        System.out.println("并行归约: " + measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs" );
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L,i -> i+1)
                .limit(n)
                .parallel()
                .reduce(0L,Long::sum);
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L,i -> i+1)
                .limit(n)
                .reduce(0L,Long::sum);
    }

    public static long iterativeSum(long n) { long result = 0;
        for (long i = 1L; i <= n; i++) { result += i;
        }
        return result; }

    public static long measureSumPerf(Function<Long, Long> adder, long n) { long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000; System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest; }
}
