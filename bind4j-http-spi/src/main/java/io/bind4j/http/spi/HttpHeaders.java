package io.bind4j.http.spi;

/**
 * HTTP header constants.
 *
 * @since 0.1.0
 */
public final class HttpHeaders {
  private HttpHeaders() {}

  public static final String CONTENT_TYPE = "Content-Type";
  public static final String CONTENT_LENGTH = "Content-Length";
  public static final String AUTHORIZATION = "Authorization";
  public static final String ACCEPT = "Accept";
  public static final String USER_AGENT = "User-Agent";
  public static final String CACHE_CONTROL = "Cache-Control";
  public static final String LOCATION = "Location";
  public static final String SET_COOKIE = "Set-Cookie";
  public static final String COOKIE = "Cookie";
  public static final String X_FORWARDED_FOR = "X-Forwarded-For";
  public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
  public static final String X_REQUEST_ID = "X-Request-Id";
  public static final String X_CORRELATION_ID = "X-Correlation-Id";
}
