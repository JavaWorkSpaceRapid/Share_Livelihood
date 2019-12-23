package share.base.extensions;


import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ObjectExtension {

    public static boolean isNull(Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static String asString(Object o) {
        if (ObjectExtension.isNotNull(o))
            return o.toString();
        return null;
    }

    public static boolean isValueType(Object obj) {
        return (obj instanceof Character || obj instanceof String || obj instanceof Number || obj instanceof Boolean);
    }

    public static Object nvl(Object obj, Object val) {
        if (isNull(obj)) return val;
        return obj;
    }

    public static Object asObject(Map map, Class<?> beanClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (map == null)
            return null;
        Object obj = beanClass.newInstance();
        BeanUtils.populate(obj, map);
        return obj;
    }

    public static Map<?, ?> asMap(Object obj) {
        if (obj == null)
            return null;
        return new BeanMap(obj);
    }
}
