package com.agiledeveloper;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {
  public static void main(String[] args) {

    List<String> symbols = Arrays.asList("AMZN", "GOOG", "INTC", "MSFT");

    Observable<StockInfo> feed = StockServer.getFeed(symbols);

    feed.subscribe(System.out::println);
  }
}