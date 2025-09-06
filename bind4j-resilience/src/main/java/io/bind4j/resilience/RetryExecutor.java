package io.bind4j.resilience;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Execute a task with retry and fixed backoff.
 *
 * @since 0.1.0
 */
public final class RetryExecutor {
  private final RetryPolicy policy;

  public RetryExecutor(RetryPolicy policy) {
    this.policy = Objects.requireNonNull(policy, "policy");
  }

  public <T> T execute(Callable<T> task) throws Exception {
    int attempts = 0;
    Exception last = null;
    int max = Math.max(1, policy.maxAttempts());
    while (attempts < max) {
      try {
        return task.call();
      } catch (Exception e) {
        last = e;
        attempts++;
        if (attempts >= max) break;
        long backoff = Math.max(0L, policy.backoffMs());
        if (backoff > 0L) Thread.sleep(backoff);
      }
    }
    throw last;
  }
}
