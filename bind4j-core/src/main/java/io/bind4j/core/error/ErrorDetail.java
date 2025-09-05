package io.bind4j.core.error;

/**
 * Describe a transport-neutral error generalized from RFC 9457.
 *
 * @since 0.1.0
 */
public record ErrorDetail(String type, String title, int code, String detail, String traceId) {}
