package com.vincentbrison.openlibraries.android.dualcache.lib;

import java.util.concurrent.Callable;

/**
 * Created by vnicolaou on 08/05/16.
 */
public class SafeCache<T> {

    private final DualCache<T> cache;

    protected SafeCache(DualCache<T> mDualCache) {
        this.cache = mDualCache;
    }

    public T get(String key, Callable<T> callable, boolean cacheNulls) {
        T obj;
        synchronized (key.intern()) {
            obj = cache.get(key);
            if (obj == null) {
                try {
                    obj = callable.call();
                    if (obj != null || cacheNulls)
                        cache.put(key, obj);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return obj;
    }

    public T get(String key, Callable<T> callable) {
        return get(key, callable, false);
    }

}
