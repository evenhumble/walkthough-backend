package io.qkits.corejava.corejava.concurrency.patterns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * QMETA
 * created at: 2020/4/25
 * created by: patrick
 **/
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface GuardedBy {
    String value();
}
