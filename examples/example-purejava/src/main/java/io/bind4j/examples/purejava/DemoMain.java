package io.bind4j.examples.purejava;

import io.bind4j.http.spi.HttpMethod;
import io.bind4j.http.spi.HttpResponses;
import io.bind4j.http.undertow.UndertowServerAdapter;

public final class DemoMain {
  public static void main(String[] args) {
    var server =
        new UndertowServerAdapter()
            .route(
                HttpMethod.GET,
                "/hello",
                req -> {
                  String name = req.queryParam("name").orElse("there");
                  return HttpResponses.ok("Hi " + name);
                });
    server.start(8080);
  }
}
