package com.dc.sbn.netty.core;

import java.lang.annotation.*;

/**
 * 类型spring mvc里面的@ReqestMapping
 *
 * @author duchao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ActionMap {
    int key();
}
