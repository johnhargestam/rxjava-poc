package com.johnhargestam;

import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AsyncServiceTest {

  AsyncService asyncService;
  TestObserver<String> testObserver;

  @BeforeEach
  void beforeEach() {
    asyncService = new AsyncService();
    testObserver = new TestObserver<>();
  }

  @Test
  void testSyncHello() {
    asyncService.syncHello("John").subscribe(testObserver);

    testObserver.assertComplete();
    testObserver.assertValue("Hello John");
  }
}