package com.ctsi.utils;

import java.lang.annotation.*;

/**
 * ClassName ExcelColumn
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/24 15:48
 * Version v1.0
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    String value() default "";
    int col() default 0;
}