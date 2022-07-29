package io.vertx.lang.rx;

public class Assertion {
  public static void assertDoesNotThrow(Runnable action) {
    try {
      action.run();
    } catch (Exception ex) {
      throw new Error("Unexpected exception: ", ex);
    }
  }
}
