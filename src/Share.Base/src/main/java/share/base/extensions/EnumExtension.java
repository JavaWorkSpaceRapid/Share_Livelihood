package share.base.extensions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class EnumExtension {
    public static Object getDescription(Enum enumm, String name, Class clazz) {
        try {
            String enumName = enumm.name();
            Annotation annotation = enumm.getClass().getField(enumName).getAnnotation(clazz);
            Method method = clazz.getMethod(name);
            Object result = method.invoke(annotation);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}
