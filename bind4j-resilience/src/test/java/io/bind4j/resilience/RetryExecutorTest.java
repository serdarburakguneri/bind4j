package io.bind4j.resilience;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class RetryExecutorTest {

  @Test
  void succeedsWithoutRetry() throws Exception {
    var executor = new RetryExecutor(RetryPolicy.of(3, 10));
    String out = executor.execute(() -> "ok");
    assertEquals("ok", out);
  }

  @Test
  void retriesThenSucceeds() throws Exception {
    var executor = new RetryExecutor(RetryPolicy.of(3, 1));
    AtomicInteger calls = new AtomicInteger();
    String out =
        executor.execute(
            () -> {
              if (calls.getAndIncrement() < 2) throw new IllegalStateException("boom");
              return "ok";
            });
    assertEquals("ok", out);
    assertEquals(3, calls.get());
  }

  @Test
  void exceedsAttemptsThrowsLast() {
    var executor = new RetryExecutor(RetryPolicy.of(2, 1));
    AtomicInteger calls = new AtomicInteger();
    var ex =
        assertThrows(
            IllegalStateException.class,
            () ->
                executor.execute(
                    () -> {
                      calls.incrementAndGet();
                      throw new IllegalStateException("boom");
                    }));
    assertEquals("boom", ex.getMessage());
    assertEquals(2, calls.get());
  }
}
