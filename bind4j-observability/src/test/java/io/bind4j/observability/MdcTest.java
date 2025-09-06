package io.bind4j.observability;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

class MdcTest {

  @Test
  void putGetRemove() {
    Mdc.put(MdcKeys.TRACE_ID, "t-1");
    assertEquals("t-1", Mdc.get(MdcKeys.TRACE_ID));
    Mdc.remove(MdcKeys.TRACE_ID);
    assertNull(Mdc.get(MdcKeys.TRACE_ID));
  }

  @Test
  void withRestoresPreviousContext() throws Exception {
    MDC.clear();
    MDC.put("existing", "x");
    try (AutoCloseable c = Mdc.with(Map.of(MdcKeys.TRACE_ID, "t-2", MdcKeys.USER_ID, "u-9"))) {
      assertEquals("t-2", Mdc.get(MdcKeys.TRACE_ID));
      assertEquals("u-9", Mdc.get(MdcKeys.USER_ID));
      assertEquals("x", Mdc.get("existing"));
    }
    assertEquals("x", Mdc.get("existing"));
    assertNull(Mdc.get(MdcKeys.TRACE_ID));
    assertNull(Mdc.get(MdcKeys.USER_ID));
  }
}
