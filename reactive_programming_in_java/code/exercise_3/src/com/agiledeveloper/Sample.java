package com.agiledeveloper;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class Sample {
  public static void main(String[] args) {

    List<String> symbols = Arrays.asList("AMZN", "GOOG", "INTC", "MSFT");

    Observable<StockInfo> feed = StockServer.getFeed(symbols);

    feed.subscribe(new Observer<StockInfo>() {
      private Disposable disposable;

      @Override
      public void onSubscribe(Disposable disposable) {
        this.disposable = disposable;
      }

      @Override
      public void onNext(StockInfo stockInfo) {
        System.out.println(stockInfo);

        //comment out the following four line to see the completed signal come through
        if(stockInfo.value < 100) {
          System.out.println("un-subscribing...");
          disposable.dispose();
        }
      }

      @Override
      public void onError(Throwable throwable) {
      }

      @Override
      public void onComplete() {
        System.out.println("DONE");
      }
    });
  }
}