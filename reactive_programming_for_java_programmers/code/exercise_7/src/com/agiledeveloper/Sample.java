package com.agiledeveloper;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Sample {
  public static void slowUseOfInfo(StockInfo stockInfo) {
    System.out.println(stockInfo + " --- " + Thread.currentThread());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {

    List<String> symbols = Arrays.asList("AMZN", "GOOG", "ORCL");

    Observable<StockInfo> feed = StockServer.getFeed(symbols);

    feed.subscribe(new Subscriber<StockInfo>() {
      @Override
      public void onStart() {
        request(0);
      }

      @Override
      public void onCompleted() {
        System.out.println("done");
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println(throwable);
      }

      @Override
      public void onNext(StockInfo stockInfo) {
        slowUseOfInfo(stockInfo);
        int index = symbols.indexOf(stockInfo.ticker);
        request((index + 1) % symbols.size());
      }
    });
  }
}