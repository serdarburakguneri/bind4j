package io.bind4j.observability;

import java.util.Map;
import java.util.Objects;
import org.slf4j.MDC;

/**
 * MDC utilities.
 *
 * @since 0.1.0
 */
public final class Mdc {
  private Mdc() {}

  public static void put(String key, String value) {
    if (value == null) return;
    MDC.put(key, value);
  }

  public static void remove(String key) {
    MDC.remove(key);
  }

  public static String get(String key) {
    return MDC.get(key);
  }

  public static AutoCloseable with(Map<String, String> values) {
    Objects.requireNonNull(values, "values");
    Map<String, String> previous = MDC.getCopyOfContextMap();
    values.forEach(Mdc::put);
    return () -> {
      MDC.clear();
      if (previous != null) MDC.setContextMap(previous);
    };
  }
}
