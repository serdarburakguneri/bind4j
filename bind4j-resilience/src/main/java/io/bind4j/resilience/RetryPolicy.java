package io.bind4j.resilience;

import io.bind4j.annotations.Retry;

/**
 * Retry policy (max attempts and backoff in milliseconds).
 *
 * @since 0.1.0
 */
public record RetryPolicy(int maxAttempts, long backoffMs) {
  public static RetryPolicy of(int maxAttempts, long backoffMs) {
    return new RetryPolicy(maxAttempts, backoffMs);
  }

  public static RetryPolicy fromAnnotation(Retry retry) {
    if (retry == null) return new RetryPolicy(1, 0);
    return new RetryPolicy(retry.maxAttempts(), retry.backoffMs());
  }
}
