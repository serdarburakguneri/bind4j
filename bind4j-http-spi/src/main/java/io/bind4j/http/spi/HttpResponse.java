package io.bind4j.http.spi;

import java.util.List;
import java.util.Map;

/**
 * HTTP response abstraction.
 *
 * @since 0.1.0
 */
public record HttpResponse(int status, Map<String, List<String>> headers, byte[] body) {}
