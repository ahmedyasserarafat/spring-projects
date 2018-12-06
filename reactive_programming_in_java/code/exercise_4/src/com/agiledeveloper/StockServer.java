package com.agiledeveloper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.List;

public class StockServer {
  public static Observable<StockInfo> getFeed(List<String> symbols) {
    return Observable.create(emitter -> emit(emitter, symbols));
  }

  private static void emit(ObservableEmitter<StockInfo> emitter, List<String> symbols) {
    int count  = 0;
    while (count++ < 5 && !emitter.isDisposed()) {
      symbols.stream()
          .map(StockInfo::fetch)
          .forEach(emitter::onNext);

      sleep(1000);
    }

    emitter.onComplete();
  }

  private static void sleep(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}