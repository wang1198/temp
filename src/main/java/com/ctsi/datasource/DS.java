package com.ctsi.datasource;

import java.lang.annotation.*;

/**
 * ClassName DS
 * Description //TODO
 * Author tongliwei
 * Date 2020/1/31 16:51
 * Version v1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DS {
    String value() default "primary";
}

