package io.ljunggren.object.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ObjectUtils {

    @SuppressWarnings("unchecked")
    public static <T> T trimProperties(T object) throws IllegalArgumentException, IllegalAccessException {
        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            String string = ((String) object).trim();
            return (T) string;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field: fields) {
            if (field != null && field.getType() == String.class && !Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                String value = (String) field.get(object);
                field.set(object, value == null ? value : value.trim());
            }
        }
        return object;
    }
    
}
