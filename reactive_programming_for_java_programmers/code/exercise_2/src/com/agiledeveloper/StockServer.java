package com.agiledeveloper;

import rx.Observable;

import java.util.List;

public class StockServer {
  public static Observable<StockInfo> getFeed(List<String> symbols) {
    return Observable.create(subscriber -> {
      while(true) {
        symbols.stream()
            .map(StockInfo::fetch)
            .forEach(subscriber::onNext);

        sleep(1000);
      }});
  }

  private static void sleep(int ms) {
    try { Thread.sleep(ms); } catch(Exception ex) {}
  }
}
