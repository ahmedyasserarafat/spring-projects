package com.agiledeveloper;

import rx.Observable;

import java.util.List;

public class StockServer {
  public static Observable<StockInfo> getFeed(List<String> symbols) {
    return Observable.create(
        subscriber -> {
          subscriber.setProducer(request -> {
            //the request generally mean the number of values to return.
            //That often leads to keeping some mutable state here. Instead of doing that
            //I am using request as the index in symbols to return value for.

            int index = (int) request;
            subscriber.onNext(StockInfo.fetch(symbols.get(index)));
          });
        });
  }
}
