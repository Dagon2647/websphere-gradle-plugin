package org.frayer.gradle.plugins.utils.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: apimenov
 * Date: 12.12.13
 * Time: 20:27
 *  Copyright (c) ZAO "Cinimex-Informatica"
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AntPropety {
    String value() default "";

    boolean required() default false;
}