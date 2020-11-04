package jie.flyer.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author kain
 * @since 2019-11-04
 */
public class ClassUtils {

    public static List<Field> getAllFields(Class<?> objClass) {
        if (objClass == null) {
            return Collections.emptyList();
        }
        List<Field> fields = new ArrayList<>();
        while (!objClass.isAssignableFrom(Object.class)) {
            fields.addAll(Arrays.asList(objClass.getDeclaredFields()));
            objClass = objClass.getSuperclass();
        }
        return fields;
    }

    public static void fillFieldValue(Object obj, Field field, Object value) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
            Method method = pd.getWriteMethod();
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(Object obj, String fieldName) {
        if (obj == null || fieldName == null) {
            return null;
        }
        try {
            Class<?> clazz = obj.getClass();
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            return pd.getReadMethod().invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldValue(Field field, Object obj) {
        return field == null ? null : getValue(obj, field.getName());
    }

    public static List<Field> filterFields(Class<?> objClass, Class<?> filter) {
        return filterFields(objClass, field -> filter.equals(field.getType()));
    }

    public static List<Field> filterFields(Class<?> targetClass, Predicate<Field> predicate) {
        return CollectionUtils.filter(getAllFields(targetClass), predicate);
    }

    public static boolean match(Object value, Class<?> targetClass) {
        return value == null || value.getClass().equals(targetClass);
    }

    public static boolean isJavaClass(Class<?> clz) {
        String className = clz.getName();
        String[] nameArr = className.split("\\.");
        return nameArr.length <= 1 || nameArr[0].equalsIgnoreCase("java");
    }

    public static Object fieldGet(Field field, Object obj) {
        if (field == null || obj == null) {
            return null;
        }
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void fieldSet(Field field, Object obj, Object value) {
        if (field == null || obj == null) {
            return;
        }
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
