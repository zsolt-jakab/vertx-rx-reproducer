package io.vertx.reactivex.test;

import io.vertx.core.Vertx;
import io.vertx.lang.rx.Assertion;
import io.vertx.rxjava3.core.streams.ReadStream;
import org.junit.Test;

public class ReadStreamTest {

  @Test
  public void testCallReadStreamHandlerShouldNotThrowExceptionWhenParamNull() {
    Vertx vertx = Vertx.vertx();
    ReadStream<Long> readStream = ReadStream.newInstance(vertx.periodicStream(1L));

    Assertion.assertDoesNotThrow(() -> readStream.handler(null));
  }
}
