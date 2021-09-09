package com.johnhargestam;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class NumberDebounceService {
  private final Scheduler scheduler;
  private final long time;
  private final TimeUnit timeUnit;

  public NumberDebounceService(long time, TimeUnit timeUnit) {
    this(time, timeUnit, Schedulers.computation());
  }

  public NumberDebounceService(long time, TimeUnit timeUnit, Scheduler scheduler) {
    this.time = time;
    this.timeUnit = timeUnit;
    this.scheduler = scheduler;
  }

  public Observable<Long> getIncreasingNumbers() {
    return Observable.interval(0L, time, timeUnit, scheduler)
        .startWithItem(-1L)
        .map(i -> i + 1);
  }
}
