package io.bind4j.core.interceptor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class InterceptorChainTest {

  @Test
  void executesInOrderAndAllowsAroundLogic() throws Exception {
    List<String> log = new ArrayList<>();

    Interceptor<String, String> i1 =
        (ctx, chain) -> {
          log.add("i1-before:" + ctx);
          String result = chain.proceed(ctx + "|i1");
          log.add("i1-after");
          return result + "|i1";
        };

    Interceptor<String, String> i2 =
        (ctx, chain) -> {
          log.add("i2-before:" + ctx);
          String result = chain.proceed(ctx + "|i2");
          log.add("i2-after");
          return result + "|i2";
        };

    Interceptor.FinalHandler<String, String> terminal =
        ctx -> {
          log.add("terminal:" + ctx);
          return "ok";
        };

    var chain = Interceptor.newChain("start", List.of(i1, i2), terminal);
    String result = chain.proceed("start");

    assertEquals("ok|i2|i1", result);
    assertEquals(
        List.of(
            "i1-before:start",
            "i2-before:start|i1",
            "terminal:start|i1|i2",
            "i2-after",
            "i1-after"),
        log);
  }

  @Test
  void propagatesExceptions() {
    Interceptor<String, String> i = (ctx, chain) -> chain.proceed(ctx);
    Interceptor.FinalHandler<String, String> terminal =
        ctx -> {
          throw new IllegalStateException("boom");
        };
    var chain = Interceptor.newChain("c", List.of(i), terminal);
    var ex = assertThrows(IllegalStateException.class, () -> chain.proceed("c"));
    assertEquals("boom", ex.getMessage());
  }
}
