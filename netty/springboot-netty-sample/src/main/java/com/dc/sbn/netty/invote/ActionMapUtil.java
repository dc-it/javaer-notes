package com.dc.sbn.netty.invote;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * netty请求分发器
 *
 * @author duchao
 */
public class ActionMapUtil {

    private static Map<Integer, Action> map = new HashMap<Integer, Action>();

    public static Object invote(Integer key, Object... args) throws Exception {
        Action action = map.get(key);
        if (action != null) {
            Method method = action.getMethod();
            try {
                return method.invoke(action.getObject(), args);
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    public static void put(Integer key, Action action) {
        map.put(key, action);
    }

}
