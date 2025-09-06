package io.bind4j.http.spi;

/**
 * Functional HTTP handler.
 *
 * @since 0.1.0
 */
@FunctionalInterface
public interface HttpHandler {
  HttpResponse handle(HttpRequest request) throws Exception;
}
