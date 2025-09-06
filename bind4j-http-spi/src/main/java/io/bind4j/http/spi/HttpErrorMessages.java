package io.bind4j.http.spi;

/**
 * HTTP error messages and status text.
 *
 * @since 0.1.0
 */
public final class HttpErrorMessages {
  private HttpErrorMessages() {}

  /** Common HTTP error messages. */
  public static final class Messages {
    private Messages() {}

    public static final String NOT_FOUND = "Not Found";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String METHOD_NOT_ALLOWED = "Method Not Allowed";
    public static final String CONFLICT = "Conflict";
    public static final String UNPROCESSABLE_ENTITY = "Unprocessable Entity";
    public static final String SERVICE_UNAVAILABLE = "Service Unavailable";
    public static final String GATEWAY_TIMEOUT = "Gateway Timeout";
  }

  /** HTTP status text as defined in RFC 7231. */
  public static final class StatusText {
    private StatusText() {}

    // 2xx Success
    public static final String OK = "OK";
    public static final String CREATED = "Created";
    public static final String ACCEPTED = "Accepted";
    public static final String NO_CONTENT = "No Content";

    // 3xx Redirection
    public static final String MOVED_PERMANENTLY = "Moved Permanently";
    public static final String FOUND = "Found";
    public static final String NOT_MODIFIED = "Not Modified";

    // 4xx Client Error
    public static final String BAD_REQUEST = "Bad Request";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String NOT_FOUND = "Not Found";
    public static final String METHOD_NOT_ALLOWED = "Method Not Allowed";
    public static final String CONFLICT = "Conflict";
    public static final String UNPROCESSABLE_ENTITY = "Unprocessable Entity";

    // 5xx Server Error
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String BAD_GATEWAY = "Bad Gateway";
    public static final String SERVICE_UNAVAILABLE = "Service Unavailable";
    public static final String GATEWAY_TIMEOUT = "Gateway Timeout";
  }
}
