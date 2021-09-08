package com.johnhargestam;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class HelloServiceTest {

  HelloService helloService;
  TestObserver<String> testObserver;
  TestScheduler testScheduler;

  @BeforeEach
  void beforeEach() {
    testScheduler = new TestScheduler();
    helloService = new HelloService(testScheduler);
    testObserver = new TestObserver<>();
  }

  @Test
  void testSyncHello() {
    helloService.syncHello("John").subscribe(testObserver);

    testObserver.assertComplete();
    testObserver.assertValue("Hello John");
  }

  @Test
  void testAsyncHello() {
    helloService.asyncHello("John", 10, TimeUnit.SECONDS).subscribe(testObserver);

    testScheduler.advanceTimeBy(10, TimeUnit.SECONDS);
    testObserver.assertComplete();
    testObserver.assertValue("Hello John");
  }
}