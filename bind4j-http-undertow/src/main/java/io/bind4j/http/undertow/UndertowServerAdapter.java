package io.bind4j.http.undertow;

import io.bind4j.http.spi.HttpHandler;
import io.bind4j.http.spi.HttpRequest;
import io.bind4j.http.spi.HttpResponse;
import io.bind4j.http.spi.HttpServerAdapter;
import io.bind4j.http.spi.HttpStatus;
import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.BlockingHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Undertow-based server adapter.
 *
 * @since 0.1.0
 */
public final class UndertowServerAdapter implements HttpServerAdapter {
  private static final Logger log = LoggerFactory.getLogger(UndertowServerAdapter.class);

  private final Map<String, Map<String, HttpHandler>> routes = new ConcurrentHashMap<>();
  private Undertow server;

  @Override
  public HttpServerAdapter route(String method, String path, HttpHandler handler) {
    routes.computeIfAbsent(method.toUpperCase(), m -> new ConcurrentHashMap<>()).put(path, handler);
    return this;
  }

  @Override
  public void start(int port) {
    this.server =
        Undertow.builder()
            .addHttpListener(port, "0.0.0.0")
            .setHandler(new BlockingHandler(this::handle))
            .build();
    server.start();
    log.info("HTTP server started on port {}", port);
  }

  @Override
  public void stop() {
    if (server != null) {
      server.stop();
      log.info("HTTP server stopped");
    }
  }

  private void handle(HttpServerExchange exchange) {
    String method = exchange.getRequestMethod().toString().toUpperCase();
    String path = exchange.getRequestPath();
    var methodRoutes = routes.get(method);
    var handler = methodRoutes != null ? methodRoutes.get(path) : null;
    if (handler == null) {
      exchange.setStatusCode(HttpStatus.NOT_FOUND);
      exchange.getResponseSender().send(HttpStatus.getStatusText(HttpStatus.NOT_FOUND));
      return;
    }
    try {
      HttpRequest req = UndertowHttpConverter.toRequest(exchange);
      HttpResponse res = handler.handle(req);
      UndertowHttpConverter.applyResponse(exchange, res);
    } catch (Exception e) {
      log.error("Handler error", e);
      exchange.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      exchange.getResponseSender().send(HttpStatus.getStatusText(HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }
}
