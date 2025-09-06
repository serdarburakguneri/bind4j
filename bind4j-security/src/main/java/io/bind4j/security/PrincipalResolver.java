package io.bind4j.security;

import java.util.Optional;

/**
 * Resolve the current principal from an invocation-specific context.
 *
 * @param <C> invocation context type (e.g., HTTP request, message, RPC call)
 * @since 0.1.0
 */
public interface PrincipalResolver<C> {
  Optional<Principal> resolve(C context);
}
