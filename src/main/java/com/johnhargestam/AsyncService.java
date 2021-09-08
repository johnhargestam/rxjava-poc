package com.johnhargestam;

import io.reactivex.rxjava3.core.Observable;

public class AsyncService {
  public Observable<String> syncHello(String name) {
    return Observable.fromCallable(() -> "Hello " + name);
  }
}
