package io.bind4j.core.interceptor;

import java.util.List;
import java.util.Objects;

/**
 * Around-invocation middleware for cross-cutting concerns.
 *
 * @param <C> context or command type (e.g., HTTP request, message, query)
 * @param <R> result type returned by the terminal handler
 * @since 0.1.0
 */
public interface Interceptor<C, R> {

  /**
   * Intercepts the invocation. Implementations should usually call {@link Chain#proceed(Object)}
   * exactly once to continue the pipeline. Implementations may short-circuit by returning a value
   * without calling {@code proceed} or by throwing an exception.
   */
  R intercept(C context, Chain<C, R> chain) throws Exception;

  /** Invocation pipeline that passes to the next interceptor or terminal handler. */
  interface Chain<C, R> {
    /** Current context instance. */
    C context();

    /** Invoke the next interceptor or the terminal handler. */
    R proceed(C context) throws Exception;
  }

  /** Create a new chain from interceptors and a final handler. */
  static <C, R> Chain<C, R> create(
      C context, List<Interceptor<C, R>> interceptors, FinalHandler<C, R> finalHandler) {
    Objects.requireNonNull(finalHandler, "finalHandler");
    var safeList = List.copyOf(interceptors == null ? List.of() : interceptors);
    return new DefaultChain<>(context, safeList, 0, finalHandler);
  }

  /** Final handler invoked after the last interceptor. */
  @FunctionalInterface
  interface FinalHandler<C, R> {
    R handle(C context) throws Exception;
  }

  /** Default chain implementation. */
  final class DefaultChain<C, R> implements Chain<C, R> {
    private final C initialContext;
    private final List<Interceptor<C, R>> interceptors;
    private final int index;
    private final FinalHandler<C, R> finalHandler;

    DefaultChain(
        C initialContext,
        List<Interceptor<C, R>> interceptors,
        int index,
        FinalHandler<C, R> finalHandler) {
      this.initialContext = initialContext;
      this.interceptors = interceptors;
      this.index = index;
      this.finalHandler = finalHandler;
    }

    @Override
    public C context() {
      return initialContext;
    }

    @Override
    public R proceed(C context) throws Exception {
      if (index < interceptors.size()) {
        var next = new DefaultChain<>(context, interceptors, index + 1, finalHandler);
        return interceptors.get(index).intercept(context, next);
      }
      return finalHandler.handle(context);
    }
  }
}
