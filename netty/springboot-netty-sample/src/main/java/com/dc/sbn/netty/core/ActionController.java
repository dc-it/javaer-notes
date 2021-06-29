package com.dc.sbn.netty.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * netty服务请求控制器
 *
 * @author duchao
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface ActionController {
}
