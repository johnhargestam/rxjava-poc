package com.johnhargestam;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class NumberDebounceServiceTest {

  final long TIME = 10;
  final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

  NumberDebounceService numberDebounceService;
  TestObserver<Long> testObserver;
  TestScheduler testScheduler;

  @BeforeEach
  void beforeEach() {
    testScheduler = new TestScheduler();
    numberDebounceService = new NumberDebounceService(TIME, TIME_UNIT, testScheduler);
    testObserver = new TestObserver<>();
  }

  @Test
  void testReturnsInitialValueImmediately() {
    numberDebounceService.getIncreasingNumbers().take(1).subscribe(testObserver);

    testObserver.assertComplete();
    testObserver.assertValue(0L);
  }

  @Test
  void testIncrementsOnNext() {
    numberDebounceService.getIncreasingNumbers().take(3).subscribe(testObserver);

    testScheduler.advanceTimeBy(TIME * 2, TIME_UNIT);
    testObserver.assertComplete();
    testObserver.assertValues(0L, 1L, 2L);
  }

}