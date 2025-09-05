package io.bind4j.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Messaging consumer declaration (name and group).
 *
 * <p>Used by messaging adapters to bind the handler to a subject/topic/queue and group.
 *
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Consumer {
  String name();

  String group();
}
