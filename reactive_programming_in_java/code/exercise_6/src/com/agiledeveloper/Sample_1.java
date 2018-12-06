package com.agiledeveloper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

public class Sample {
  public static void main(String[] args) throws InterruptedException {

    List<String> symbols = Arrays.asList("AMZN", "GOOG", "INTC", "MSFT");

    Observable<StockInfo> feed = StockServer.getFeed(symbols);

    feed
        .subscribeOn(Schedulers.io())
        .subscribe(
          System.out::println,
          error -> System.out.println("ERROR: " + error),
          () -> System.out.println("DONE")
    );

    System.out.println("Main is not running the function pipeline anymore");
    Thread.sleep(50000);
  }
}