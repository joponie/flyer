package jie.flyer.common.util;


import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kain
 * @since 2019-11-27
 */
public class MapUtils {
    private static Logger logger = LoggerFactory.getLogger(MapUtils.class);

    public static <K, V> Map<K, V> getColsMap(Map<K, V> sourceMap, K... keys) {
        if (keys == null || keys.length == 0 || sourceMap == null) {
            return sourceMap;
        }
        Map<K, V> resultMap = new HashMap<K, V>();
        for (K key : keys) {
            resultMap.put(key, sourceMap.get(key));
        }
        return resultMap;
    }

    /**
     * 将javabean实体类转为map类型，然后返回一个map类型的值
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            logger.error("将bean转成map出错：", e);
        }
        return params;
    }

    public static <K, V> void removeKeys(Map<K, V> source, K... keys) {
        if (source == null || keys == null || keys.length == 0) {
            return;
        }
        for (K key : keys) {
            source.remove(key);
        }
    }


    public static <K> Map toMap(K key, Object obj) {
        Map<K, Object> resultMap = new HashMap<>();
        resultMap.put(key, obj);
        return resultMap;
    }

    public static <K, V, T extends V> T getValue(Map<K, V> map, K key) {
        V value = getValue(map, key, null);
        return value == null ? null : (T) value;
    }

    public static <K, V> V getValue(Map<K, V> map, K key, V defaultValue) {
        if (valueNULL(map, key)) {
            return defaultValue;
        } else {
            return map.get(key);
        }
    }


    public static <K, V> boolean valueNULL(Map<K, V> map, K key) {
        return (map == null || key == null || !map.containsKey(key) || map.get(key) == null);
    }

    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    public static <K, V> void removeKeys(Map<K, V> map, Collection<K> keys) {
        if (isEmpty(map) || CollectionUtils.isEmpty(keys)) {
            return;
        }
        keys.forEach(map::remove);
    }

}
