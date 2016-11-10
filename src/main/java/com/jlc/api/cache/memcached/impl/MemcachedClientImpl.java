package com.jlc.api.cache.memcached.impl;

import java.util.Date;
import java.util.Map;
import com.danga.MemCached.MemCachedClient;
import com.jlc.api.cache.memcached.IMemcachedClient;

public class MemcachedClientImpl implements IMemcachedClient {

    private MemCachedClient cacheProvider;

    public MemCachedClient getCacheProvider() {
        return cacheProvider;
    }

    public void setCacheProvider(MemCachedClient cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public boolean add(String key, Object value) {
        return cacheProvider.add(key, value);
    }

    public boolean add(String key, Object value, Integer expire) {
        return cacheProvider.add(key, value, getValidDate(expire));
    }

    public boolean add(String key, Object value, Date expire) {
        return cacheProvider.add(key, value, expire);
    }

    public boolean delete(String key) {
        return cacheProvider.delete(key);
    }

    public boolean exist(String key) {
        return cacheProvider.keyExists(key);
    }

    public Object get(String key) {
        return cacheProvider.get(key);
    }

    public Map<String, Object> get(String[] keys) {
        return cacheProvider.getMulti(keys);
    }

    public boolean replace(String key, Object value) {
        return cacheProvider.replace(key, value);
    }

    public boolean replace(String key, Object value, Integer expire) {
        return cacheProvider.replace(key, value, getValidDate(expire));
    }

    public boolean set(String key, Object value) {
        return cacheProvider.set(key, value);
    }

    public boolean set(String key, Object value, Integer expire) {
        return cacheProvider.set(key, value, getValidDate(expire));
    }

    public boolean set(String key, Object value, Date expire) {
        return cacheProvider.set(key, value, expire);
    }

    /**
     * 获取绝对过期时间
     * 
     * @param s
     *                秒
     * @return
     */
    private Date getValidDate(int s) {
        return new Date(System.currentTimeMillis() + s * 1000);
    }

}
