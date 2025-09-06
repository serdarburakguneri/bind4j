package io.bind4j.http.spi;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * HTTP request abstraction.
 *
 * @since 0.1.0
 */
public interface HttpRequest {
  String method();

  String path();

  Map<String, List<String>> query();

  Map<String, List<String>> headers();

  byte[] body();

  default Optional<String> queryParam(String name) {
    var values = query().get(name);
    if (values == null || values.isEmpty()) return Optional.empty();
    return Optional.ofNullable(values.getFirst());
  }
}
