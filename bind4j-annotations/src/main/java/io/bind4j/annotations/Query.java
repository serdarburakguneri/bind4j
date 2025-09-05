package io.bind4j.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declare a read-only data operation.
 *
 * <p>Used by data adapters (e.g., database, cache) to execute a read and to tag
 * logs/traces/metrics. The value is an engine-specific query string or identifier (SQL, template
 * name, key pattern).
 *
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Query {
  String value();
}
