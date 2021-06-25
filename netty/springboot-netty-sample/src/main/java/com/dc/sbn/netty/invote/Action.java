package com.dc.sbn.netty.invote;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * 请求分发对象
 *
 * @author duchao
 */
@Data
public class Action {

    private Method method;

    private Object object;
}
