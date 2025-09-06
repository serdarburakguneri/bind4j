package io.bind4j.http.spi;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Response helpers.
 *
 * @since 0.1.0
 */
public final class HttpResponses {
  private HttpResponses() {}

  public static HttpResponse ok(String text) {
    return simple(HttpStatus.OK, MediaType.TEXT_PLAIN_UTF8, text);
  }

  public static HttpResponse json(int status, String json) {
    return simple(status, MediaType.APPLICATION_JSON_UTF8, json);
  }

  public static HttpResponse simple(int status, String contentType, String body) {
    return new HttpResponse(
        status,
        Map.of(HttpHeaders.CONTENT_TYPE, List.of(contentType)),
        body == null ? new byte[0] : body.getBytes(StandardCharsets.UTF_8));
  }
}
