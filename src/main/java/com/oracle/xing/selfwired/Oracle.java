package com.oracle.xing.selfwired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liuyong
 * 2018-12-07  11-38
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Oracle {

    String[] values();
    static String NORMAL="NORMAL";
    static String ADMIN="ADMIN";
    static String SUPER_ADMIN = "SUPER_ADMIN";

}