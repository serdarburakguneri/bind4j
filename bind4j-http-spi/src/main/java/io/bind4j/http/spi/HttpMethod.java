package io.bind4j.http.spi;

/**
 * HTTP method constants.
 *
 * @since 0.1.0
 */
public final class HttpMethod {
  private HttpMethod() {}

  public static final String GET = "GET";
  public static final String POST = "POST";
  public static final String PUT = "PUT";
  public static final String DELETE = "DELETE";
  public static final String PATCH = "PATCH";
  public static final String HEAD = "HEAD";
  public static final String OPTIONS = "OPTIONS";
  public static final String TRACE = "TRACE";
}
