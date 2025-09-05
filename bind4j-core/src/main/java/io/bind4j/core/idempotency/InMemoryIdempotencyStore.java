package io.bind4j.core.idempotency;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory {@link IdempotencyStore} for tests and single-node usage.
 *
 * @since 0.1.0
 */
public final class InMemoryIdempotencyStore<V> implements IdempotencyStore<V> {

  private static final class Entry<V> {
    final V value;
    final Instant expiresAt; // null means no expiry

    Entry(V value, Instant expiresAt) {
      this.value = value;
      this.expiresAt = expiresAt;
    }
  }

  private final Map<String, Entry<V>> store = new ConcurrentHashMap<>();

  @Override
  public Optional<V> get(String key) {
    var entry = store.get(key);
    if (entry == null) {
      return Optional.empty();
    }
    if (entry.expiresAt != null && Instant.now().isAfter(entry.expiresAt)) {
      store.remove(key);
      return Optional.empty();
    }
    return Optional.of(entry.value);
  }

  @Override
  public boolean putIfAbsent(String key, V value, Duration ttl) {
    Instant expiresAt = ttl == null ? null : Instant.now().plus(ttl);
    var newEntry = new Entry<>(value, expiresAt);
    return store.putIfAbsent(key, newEntry) == null;
  }

  @Override
  public void remove(String key) {
    store.remove(key);
  }
}
