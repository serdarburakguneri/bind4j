package io.bind4j.security;

import java.util.Set;

/**
 * Authenticated principal (id and roles).
 *
 * @since 0.1.0
 */
public interface Principal {
  String id();

  Set<String> roles();
}
