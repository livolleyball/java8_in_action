package com.lihm.java8.chapter7;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author lihuamin
 * @version 1.0
 * @date 2019/11/18 22:59
 **/
public class SpliteInAction {

    public static void main(String[] args) {
        IntStream intStream= IntStream.rangeClosed(0,10);
        Spliterator.OfInt spliterator = intStream.spliterator();
        Consumer<Integer> consumer = i->System.out.println(i);
        spliterator.forEachRemaining(consumer);
    }


    static class MySpliteratorText {
        private final String[] data;
        public MySpliteratorText(String text) {
            Objects.requireNonNull(text," thse parameter can not be null ");
            this.data= text.split("\n");
        }

        public  class MySpliterator implements Spliterator<String> {

            private  int start,end;

            public MySpliterator(){
                this.start=0;
                this.end=MySpliteratorText.this.data.length-1;
            }

            public MySpliterator(int start,int end ){
                this.start=0;
                this.end=end;
            }
//            27
            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if(start<=end){
                    start++;
                    action.accept(MySpliteratorText.this.data[start++]);
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<String> trySplit() {
                return null;
            }

            @Override
            public long estimateSize() {
                return 0;
            }

            @Override
            public int characteristics() {
                return 0;
            }
        }

    }
}
