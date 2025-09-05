package io.bind4j.core.idempotency;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class InMemoryIdempotencyStoreTest {

  @Test
  void putGetAndPreventOverwrite() {
    var store = new InMemoryIdempotencyStore<String>();
    assertTrue(store.putIfAbsent("k1", "v1", Duration.ofMinutes(1)));
    assertEquals("v1", store.get("k1").orElseThrow());
    assertFalse(store.putIfAbsent("k1", "v2", Duration.ofMinutes(1)));
    assertEquals("v1", store.get("k1").orElseThrow());
  }

  @Test
  void removeDeletesEntry() {
    var store = new InMemoryIdempotencyStore<String>();
    store.putIfAbsent("k", "v", Duration.ofMinutes(1));
    store.remove("k");
    assertTrue(store.get("k").isEmpty());
  }

  @Test
  void ttlExpiresEntry() throws InterruptedException {
    var store = new InMemoryIdempotencyStore<String>();
    assertTrue(store.putIfAbsent("k", "v", Duration.ofMillis(1)));
    Thread.sleep(10);
    assertTrue(store.get("k").isEmpty());
  }
}
