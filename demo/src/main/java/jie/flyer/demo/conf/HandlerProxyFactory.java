package jie.flyer.demo.conf;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author kain
 * @Date 2020/12/7
 **/
public class HandlerProxyFactory<T> {

    private final Class<T> mapperInterface;

    private final Map<Method, String> methodCache = new ConcurrentHashMap<>();

    public HandlerProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    protected T newInstance() {
        HandleProxy<T> proxy = new HandleProxy<>(mapperInterface, methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, proxy);
    }

}
