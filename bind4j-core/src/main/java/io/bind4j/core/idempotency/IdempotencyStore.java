package io.bind4j.core.idempotency;

import java.time.Duration;
import java.util.Optional;

/**
 * Store for idempotency keys and values with optional TTL.
 *
 * @since 0.1.0
 */
public interface IdempotencyStore<V> {

  /** Returns a previously stored value if the key exists and has not expired. */
  Optional<V> get(String key);

  /**
   * Stores a value for the given key with optional TTL semantics. If the key is already present,
   * the method must not overwrite the existing value and returns false.
   *
   * @return true if stored, false if key already existed
   */
  boolean putIfAbsent(String key, V value, Duration ttl);

  /** Removes the value if present. */
  void remove(String key);
}
