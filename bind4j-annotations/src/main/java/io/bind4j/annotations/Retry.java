package io.bind4j.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retry policy (max attempts and backoff in milliseconds).
 *
 * <p>Used by resilience adapters/pipeline to apply retry behavior.
 *
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Retry {
  int maxAttempts() default 3;

  long backoffMs() default 200L;
}
