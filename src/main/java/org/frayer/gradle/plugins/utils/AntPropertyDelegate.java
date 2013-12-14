package org.frayer.gradle.plugins.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alexey Pimenov
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AntPropertyDelegate {
}
