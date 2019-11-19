package com.lihm.java8.chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author lihuamin
 * @version 1.0
 * @date 2019-11-16 11:44
 **/
public class ParallelProcessing {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("the best process normalAdd time => " + measureSumPerformance(ParallelProcessing::normalAdd,10000000));
        System.out.println("the best process iterateStream time => " + measureSumPerformance(ParallelProcessing::iterateStream,10000000));
        System.out.println("the best process parallelStream  time => " + measureSumPerformance(ParallelProcessing::parallelStream,10000000));
        System.out.println("the best process parallelStream2 time => " + measureSumPerformance(ParallelProcessing::parallelStream2,10000000));
        System.out.println("the best process parallelStream3 time => " + measureSumPerformance(ParallelProcessing::parallelStream3,10000000));

    }

    public static long measureSumPerformance(Function<Long,Long> adder ,long limit){
        long fastest = Long.MAX_VALUE;
        for (int i=0; i<10;i++){
            long startTimestamp=System.currentTimeMillis();
            long result=adder.apply(limit);
            long duration=System.currentTimeMillis()-startTimestamp;
            System.out.println("the result of sum =>" + result);
            if (duration<fastest) fastest=duration;
        }
        return fastest;
    }

    private static long iterateStream (long limit ){
        return Stream.iterate(1L,i->i+1).limit(limit).reduce(0L,Long::sum);
    }


    private static long normalAdd(long limit ){
        long result=0L;
        for (long i =1L; i<=limit;i++){
            result +=i;
        }
        return result;
    }

    private static long parallelStream (long limit ){
        return Stream.iterate(1L,i->i+1).parallel().limit(limit).reduce(0L,Long::sum);
    }
    private static long parallelStream2 (long limit ){
//        mapToLong 拆箱
        return Stream.iterate(1L,i->i+1).parallel().mapToLong(Long::longValue).limit(limit).reduce(0L,Long::sum);
    }

    public static long parallelStream3( long limit ){
        return LongStream.rangeClosed(1,limit).parallel().reduce(0L,Long::sum);
    }

}
