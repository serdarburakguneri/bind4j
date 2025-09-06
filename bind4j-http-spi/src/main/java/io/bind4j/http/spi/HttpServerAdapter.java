package io.bind4j.http.spi;

/**
 * HTTP server adapter lifecycle.
 *
 * @since 0.1.0
 */
public interface HttpServerAdapter {
  HttpServerAdapter route(String method, String path, HttpHandler handler);

  void start(int port);

  void stop();
}
