package io.bind4j.http.spi;

/**
 * HTTP status codes and their corresponding text.
 *
 * @since 0.1.0
 */
public final class HttpStatus {
  private HttpStatus() {}

  // 2xx Success
  public static final int OK = 200;
  public static final int CREATED = 201;
  public static final int ACCEPTED = 202;
  public static final int NO_CONTENT = 204;

  // 3xx Redirection
  public static final int MOVED_PERMANENTLY = 301;
  public static final int FOUND = 302;
  public static final int NOT_MODIFIED = 304;

  // 4xx Client Error
  public static final int BAD_REQUEST = 400;
  public static final int UNAUTHORIZED = 401;
  public static final int FORBIDDEN = 403;
  public static final int NOT_FOUND = 404;
  public static final int METHOD_NOT_ALLOWED = 405;
  public static final int CONFLICT = 409;
  public static final int UNPROCESSABLE_ENTITY = 422;

  // 5xx Server Error
  public static final int INTERNAL_SERVER_ERROR = 500;
  public static final int BAD_GATEWAY = 502;
  public static final int SERVICE_UNAVAILABLE = 503;
  public static final int GATEWAY_TIMEOUT = 504;

  /**
   * Get the status text for a given status code.
   *
   * @param statusCode the HTTP status code
   * @return the status text
   */
  public static String getStatusText(int statusCode) {
    return switch (statusCode) {
      case OK -> "OK";
      case CREATED -> "Created";
      case ACCEPTED -> "Accepted";
      case NO_CONTENT -> "No Content";
      case MOVED_PERMANENTLY -> "Moved Permanently";
      case FOUND -> "Found";
      case NOT_MODIFIED -> "Not Modified";
      case BAD_REQUEST -> "Bad Request";
      case UNAUTHORIZED -> "Unauthorized";
      case FORBIDDEN -> "Forbidden";
      case NOT_FOUND -> "Not Found";
      case METHOD_NOT_ALLOWED -> "Method Not Allowed";
      case CONFLICT -> "Conflict";
      case UNPROCESSABLE_ENTITY -> "Unprocessable Entity";
      case INTERNAL_SERVER_ERROR -> "Internal Server Error";
      case BAD_GATEWAY -> "Bad Gateway";
      case SERVICE_UNAVAILABLE -> "Service Unavailable";
      case GATEWAY_TIMEOUT -> "Gateway Timeout";
      default -> "Unknown";
    };
  }
}
