package com.perkins.simplelog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Perkins
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SimpleLog {
    /**
     * INFO, ERROR, WARN, DEBUG
     * @return
     */
    String level() default "INFO";
}
