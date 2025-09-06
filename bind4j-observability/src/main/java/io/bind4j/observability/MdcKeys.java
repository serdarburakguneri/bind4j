package io.bind4j.observability;

/**
 * Standard MDC keys.
 *
 * @since 0.1.0
 */
public final class MdcKeys {
  private MdcKeys() {}

  public static final String TRACE_ID = "traceId";
  public static final String SPAN_ID = "spanId";
  public static final String USER_ID = "userId";
  public static final String EVENT_ID = "eventId";
}
