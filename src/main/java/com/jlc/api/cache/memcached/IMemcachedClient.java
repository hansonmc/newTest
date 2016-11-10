package com.jlc.api.cache.memcached;

import java.util.Date;
import java.util.Map;

/**
 * memcached缓存接口
 */
public interface IMemcachedClient {

    /**
     * 往缓存里面添加数据
     * 
     * @param key
     *                键
     * @param value
     *                值对象
     * @return
     */
    public boolean add(String key, Object value);

    /**
     * 将数据添加到cache服务器,如果保存成功则返回true,如果cache服务器存在同样key，则返回false
     * 
     * @param key
     *                键
     * @param value
     *                值对象
     * @param expire
     *                过期时间
     * @return
     */
    public boolean add(String key, Object value, Integer expire);

    /**
     * 将数据添加到cache服务器,如果保存成功则返回true,如果cache服务器存在同样key，则返回false
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean add(String key, Object value, Date expire);

    /**
     * 将数据保存到cache服务器，如果保存成功则返回true,如果cache服务器存在同样的key，则替换之
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value);

    /**
     * 将数据保存到cache服务器，如果保存成功则返回true,如果cache服务器存在同样的key，则替换之
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(String key, Object value, Integer expire);

    /**
     * 往缓存里面添加数据
     * 
     * @param key
     *                键
     * @param value
     *                值
     * @param expire
     *                过期时间（多少秒后过期）
     * @return
     */
    public boolean set(String key, Object value, Date expire);

    /**
     * 将数据替换cache服务器中相同的key,如果保存成功则返回true
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean replace(String key, Object value);

    /**
     * 将数据替换cache服务器中相同的key,如果保存成功则返回true
     * 
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean replace(String key, Object value, Integer expire);

    /**
     * 删除指定KEY的缓存值
     * 
     * @param key
     * @return
     */
    public boolean delete(String key);

    /**
     * 根据指定的KEY，返回Value
     * 
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     * @category 返回多条记录
     * @param keys
     *                记录的主键数组
     * @return Map<String, Object> 多条记录的内容
     */
    public Map<String, Object> get(String[] keys);

    /**
     * 是否存在
     * 
     * @param key
     * @return
     */
    public boolean exist(String key);

}
