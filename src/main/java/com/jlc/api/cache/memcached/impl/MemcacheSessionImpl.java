package com.jlc.api.cache.memcached.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.jlc.api.cache.memcached.IMemcachedClient;


public class MemcacheSessionImpl {

    private IMemcachedClient memcachedClient;

    public void addMemberSessionCache(HttpSession session,
        Map<String, Object> map) {
        if (session == null)
            return;
        String sessionId = session.getId();
        if (null == memcachedClient.get(sessionId)) {
            memcachedClient.add(sessionId, map, 3600);
        }
    }

    public void delMemberSessionCache(HttpSession session) {
        if (session != null) {
            memcachedClient.delete(session.getId());
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSession(HttpSession session) {
        if (session == null)
            return null;
        String sessionId = session.getId();
        if (sessionId == null)
            return null;
        return (Map<String, Object>) memcachedClient.get(sessionId);
    }

    // ---------------- set/get ----------------
    public IMemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(IMemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

}
