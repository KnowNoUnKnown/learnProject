package com.oracle.xing.selfwired.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://github.com/oraclexing
 * <p>
 * controller 注解，注入入参
 *
 *
 * @author stardust
 * @version 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

    String url();

}
