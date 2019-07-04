package com.oracle.xing.selfwired.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://github.com/oraclexing
 * <p>
 * 自定义注解
 *
 *
 * @author stardust
 * @version 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {

    String POST = "POST";
    String GET = "GET";

    /**
     * 请求方式
     * @return
     */
    String type();

    /**
     * 请求url
     * @return
     */
    String url();
}