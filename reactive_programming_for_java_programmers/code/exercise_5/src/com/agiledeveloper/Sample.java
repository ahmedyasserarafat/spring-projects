package com.agiledeveloper;

import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;

public class Sample {
  public static void main(String[] args) {

    List<String> symbols = Arrays.asList("AMZN", "GOOG", "ORCL");

    Observable<StockInfo> feed = StockServer.getFeed(symbols);

    feed.skipWhile(stockInfo -> stockInfo.value < 1900)
        .take(20)
        .subscribe(System.out::println, System.out::println);

  }
}