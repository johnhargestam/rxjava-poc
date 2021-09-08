package com.johnhargestam;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class HelloService {
  private final Scheduler scheduler;

  public HelloService()  {
    this(Schedulers.computation());
  }

  public HelloService(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  public Observable<String> syncHello(String name) {
    return Observable.fromCallable(() -> "Hello " + name);
  }

  public Observable<String> asyncHello(String name, long seconds, TimeUnit timeUnit) {
    return this.syncHello(name).delay(seconds, timeUnit, scheduler);
  }
}
