package io.vertx.lang.rx.test;


import io.vertx.lang.rx.Assertion;
import io.vertx.lang.rx.DelegatingHandler;
import org.junit.Test;

public class DelegatingHandlerTest {

  @Test
  public void testDelegatingHandlerShouldHandleNullValues() {
    Assertion.assertDoesNotThrow(() -> new DelegatingHandler(null, i -> i));
  }
}
