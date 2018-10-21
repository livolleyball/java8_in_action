## lambda 格式
匿名
函数
传递
简洁


``` java
parameter list     arrow   lambda body
   (o1,o2)          ->    o1.getColor().compareTo(o2.getColor());

 valid lambda

 (String s) -> s.length()

 (Apple a)-> a.getColor().equals("green");

 (int x,int y) ->{
    System.out.println(x);
    System.out.println(y);
 };

 () ->42

 Runnable r =()->{};

 () ->{return "hello world";}
(Integer i ) -> return "Alex" + i   invalid



```

## 函数式接口
Predicate boolean Test(T t);   T->boolean
接受泛型T对象，并返回一个 boolean。

Consumer accept(T t);        T -> void
接受泛型T对象，没有返回（void）。主要是 对对象其中的每一个元素执行操作。


Function<T,R> R apply(T,t);  T -> R
定义一个叫做 apply 的方法，它接受一个泛型 T 的对象，并返回一个泛型 R 的对象。

Supplier<T> T get();         () ->T



