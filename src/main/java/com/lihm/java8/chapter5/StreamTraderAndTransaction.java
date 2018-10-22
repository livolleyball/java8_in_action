package com.lihm.java8.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTraderAndTransaction {


    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");


        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        System.out.println("==== 2011的所有成交并按交易额排序  reverseOrder ====");
        List<Transaction> tr2011 =transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue,Comparator.reverseOrder()))
                .collect(Collectors.toList());
        System.out.println(tr2011);

        System.out.println("==== 交易员都在哪些不同的城市工作过 ====");
        List<String> cities =
                transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        System.out.println("==== 来自剑桥的交易员 ====");
        List<Trader> traders = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader->trader.getCity()=="Cambridge")
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println(traders);

        System.out.println("==== 所有交易员的姓名，并按照字母顺序排序 ====");
        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                .reduce("",(s1,s2)-> s1 +"-" + s2);

        System.out.println(traderStr);

        System.out.println("==== 有没有交易员在米兰工作过 ====");
        boolean milanBased =
                transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        System.out.println("==== 生活在剑桥的交易员的所有交易额 ====");
        transactions.stream()
                .filter(t->"Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("==== 最高交易额 ====");
        Optional<Integer> highestValue =
                transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(highestValue.get());

        System.out.println("==== 最低交易额 ====");
        Optional<Transaction> smallValue =
                transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        System.out.println(smallValue.get().getValue());

    }

}
