package com.agiledeveloper;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {
  public static void main(String[] args) {

    List<String> symbols = Arrays.asList("AMZN", "GOOG", "INTC", "MSFT");

    Observable<StockInfo> feed = StockServer.getFeed(symbols);

    feed
        .onErrorResumeNext(throwable -> {
          System.out.println("First one failed: " + throwable);
          System.out.println("Creating a backup...");
          return StockServer.getFeed(symbols);
        })
        .subscribe(
        System.out::println,
        error -> System.out.println("ERROR: " + error),
        () -> System.out.println("DONE")
    );
  }
}