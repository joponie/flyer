package com.github.joponie.flyer.common.base;

import com.github.joponie.flyer.common.base.account.Account;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kain
 * @since 2019-11-05
 */
@NoArgsConstructor
public abstract class ThreadContext {

    private static final String ACCOUNT_KEY = ThreadContext.class.getName() + "_ACCOUNT_KEY";

    private static final ThreadLocal<Map<Object, Object>> resources = new ThreadLocal<>();

    public static Map<Object, Object> getResources() {
        if (resources.get() == null) {
            return Collections.emptyMap();
        } else {
            return new HashMap<>(resources.get());
        }
    }

    public static void setResources(Map<Object, Object> newResources) {
        if (newResources == null || newResources.isEmpty()) {
            return;
        }
        ensureResourcesInitialized();
        resources.get().clear();
        resources.get().putAll(newResources);
    }

    private static void ensureResourcesInitialized() {
        if (resources.get() == null) {
            resources.set(new HashMap<>());
        }
    }

    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }

    public static Object get(Object key) {
        return getValue(key);
    }

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            remove(key);
            return;
        }
        ensureResourcesInitialized();
        resources.get().put(key, value);
    }

    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = resources.get();
        return perThreadResources != null ? perThreadResources.remove(key) : null;
    }

    public static void remove() {
        resources.remove();
    }

    public static Account getAccount() {
        return (Account) get(ACCOUNT_KEY);
    }

    public static void bind(Account account) {
        if (account != null) {
            put(ACCOUNT_KEY, account);
        }
    }

    public static Account unbindAccount() {
        return (Account) remove(ACCOUNT_KEY);
    }
}
