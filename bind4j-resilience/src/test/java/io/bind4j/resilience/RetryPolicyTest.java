package io.bind4j.resilience;

import static org.junit.jupiter.api.Assertions.*;

import io.bind4j.annotations.Retry;
import org.junit.jupiter.api.Test;

class RetryPolicyTest {

  @Retry(maxAttempts = 3, backoffMs = 200)
  static class Annotated {}

  @Test
  void fromAnnotationParsesValues() throws Exception {
    Retry a = Annotated.class.getAnnotation(Retry.class);
    var p = RetryPolicy.fromAnnotation(a);
    assertEquals(3, p.maxAttempts());
    assertEquals(200L, p.backoffMs());
  }

  @Test
  void nullAnnotationDefaults() {
    var p = RetryPolicy.fromAnnotation(null);
    assertEquals(1, p.maxAttempts());
    assertEquals(0L, p.backoffMs());
  }
}
