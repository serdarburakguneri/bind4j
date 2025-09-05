package io.bind4j.core.error;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ErrorDetailTest {

  @Test
  void recordHoldsValues() {
    var e =
        new ErrorDetail("validation-error", "Validation failed", 400, "Missing id", "trace-123");
    assertEquals("validation-error", e.type());
    assertEquals("Validation failed", e.title());
    assertEquals(400, e.code());
    assertEquals("Missing id", e.detail());
    assertEquals("trace-123", e.traceId());
  }
}
