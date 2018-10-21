package com.lihm.java8;

// T U K 3个入参数
// R 返回值


@FunctionalInterface
public interface ThreeFunction <T,U,K,R>{

    R apply(T t,U u,K k);

}
