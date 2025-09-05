package io.bind4j.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Operation identifier for tracing and metrics.
 *
 * <p>Used by adapters to name spans, metrics, and correlate logs.
 *
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Operation {
  String value();
}
