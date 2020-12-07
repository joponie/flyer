package jie.flyer.demo.conf;

import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author kain
 * @Date 2020/12/7
 **/
public class HandleProxy<T> implements InvocationHandler {

    private static final int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
            | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC;

    private static final Constructor<MethodHandles.Lookup> lookupConstructor;

    private static final Method privateLookupInMethod;

    private final Class<T> mapperInterface;

    private final Map<Method, String> methodCache;

    public HandleProxy(Class<T> mapperInterface, Map<Method, String> methodCache) {
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    static {
        Method privateLookupIn;
        try {
            privateLookupIn = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
        } catch (NoSuchMethodException e) {
            privateLookupIn = null;
        }
        privateLookupInMethod = privateLookupIn;

        Constructor<MethodHandles.Lookup> lookup = null;
        if (privateLookupInMethod == null) {
            // JDK 1.8
            try {
                lookup = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                lookup.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(
                        "There is neither 'privateLookupIn(Class, Lookup)' nor 'Lookup(Class, int)' method in java.lang.invoke.MethodHandles.",
                        e);
            } catch (Throwable t) {
                lookup = null;
            }
        }
        lookupConstructor = lookup;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            } else if (method.isDefault()) {
                if (privateLookupInMethod == null) {
                    return invokeDefaultMethodJava8(proxy, method, args);
                } else {
                    return invokeDefaultMethodJava9(proxy, method, args);
                }
            }
        } catch (Throwable t) {
            System.out.println("error");
            throw t;
        }
        String s = cachedMapperMethod(method);
        System.out.println(s);
        return null;
    }


    private String cachedMapperMethod(Method method) {
        return methodCache.computeIfAbsent(method,
                k -> method.toString());
    }

    private Object invokeDefaultMethodJava8(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Class<?> declaringClass = method.getDeclaringClass();
        return lookupConstructor.newInstance(declaringClass, ALLOWED_MODES).unreflectSpecial(method, declaringClass)
                .bindTo(proxy).invokeWithArguments(args);
    }

    private Object invokeDefaultMethodJava9(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Class<?> declaringClass = method.getDeclaringClass();
        return ((MethodHandles.Lookup) privateLookupInMethod.invoke(null, declaringClass, MethodHandles.lookup()))
                .findSpecial(declaringClass, method.getName(),
                        MethodType.methodType(method.getReturnType(), method.getParameterTypes()), declaringClass)
                .bindTo(proxy).invokeWithArguments(args);
    }
}
