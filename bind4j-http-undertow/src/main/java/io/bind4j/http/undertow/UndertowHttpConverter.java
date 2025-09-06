package io.bind4j.http.undertow;

import io.bind4j.http.spi.HttpRequest;
import io.bind4j.http.spi.HttpResponse;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for converting between Undertow's HttpServerExchange and bind4j HTTP abstractions.
 *
 * @since 0.1.0
 */
public final class UndertowHttpConverter {

  private UndertowHttpConverter() {
    // Utility class - prevent instantiation
  }

  /**
   * Converts an Undertow HttpServerExchange to a bind4j HttpRequest.
   *
   * @param exchange the Undertow exchange
   * @return the converted HttpRequest
   * @throws Exception if conversion fails
   */
  public static HttpRequest toRequest(HttpServerExchange exchange) throws Exception {
    String method = exchange.getRequestMethod().toString();
    String path = exchange.getRequestPath();

    Map<String, java.util.List<String>> query = new HashMap<>();
    exchange.getQueryParameters().forEach((k, v) -> query.put(k, new ArrayList<>(v)));

    Map<String, java.util.List<String>> headers = new HashMap<>();
    exchange
        .getRequestHeaders()
        .forEach(
            h -> {
              var list = new ArrayList<String>();
              for (String v : h) list.add(v);
              headers.put(h.getHeaderName().toString(), list);
            });

    byte[] body = readBody(exchange);

    return new HttpRequest() {
      @Override
      public String method() {
        return method;
      }

      @Override
      public String path() {
        return path;
      }

      @Override
      public Map<String, java.util.List<String>> query() {
        return query;
      }

      @Override
      public Map<String, java.util.List<String>> headers() {
        return headers;
      }

      @Override
      public byte[] body() {
        return body;
      }
    };
  }

  /**
   * Applies a bind4j HttpResponse to an Undertow HttpServerExchange.
   *
   * @param exchange the Undertow exchange
   * @param response the bind4j response to apply
   */
  public static void applyResponse(HttpServerExchange exchange, HttpResponse response) {
    exchange.setStatusCode(response.status());
    if (response.headers() != null) {
      response
          .headers()
          .forEach(
              (k, v) -> {
                var key = new HttpString(k);
                if (v == null || v.isEmpty()) {
                  exchange.getResponseHeaders().put(key, "");
                } else {
                  exchange.getResponseHeaders().put(key, v.get(0));
                  for (int i = 1; i < v.size(); i++) {
                    exchange.getResponseHeaders().add(key, v.get(i));
                  }
                }
              });
    }
    byte[] body = response.body() == null ? new byte[0] : response.body();
    exchange.getResponseSender().send(ByteBuffer.wrap(body));
  }

  /**
   * Reads the request body from an Undertow HttpServerExchange.
   *
   * @param exchange the Undertow exchange
   * @return the request body as byte array
   * @throws Exception if reading fails
   */
  public static byte[] readBody(HttpServerExchange exchange) throws Exception {
    if (!exchange.isBlocking()) exchange.startBlocking();
    var in = exchange.getInputStream();
    var out = new ByteArrayOutputStream();
    byte[] buf = new byte[4096];
    int r;
    while ((r = in.read(buf)) != -1) out.write(buf, 0, r);
    return out.toByteArray();
  }
}
