package com.example.mqttdemo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @description: 消息缓存
 * @author: Okentao
 * @create: 2021-02-28 18:07
 */
public class LocalCache {

    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(10L, TimeUnit.MINUTES)  //写入10分钟后过期
            .maximumSize(50000L)
            .build();

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static String getCache(String key) {
        return cache.getIfPresent(key);
    }

    /**
     * 获取缓存对象
     *
     * @return
     */
    public static Cache<String, String> getCache() {
        return cache;
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param obj
     */
    public static void setCache(String key, String obj) {
        cache.put(key, obj);
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public static void removeCache(String key) {
        cache.invalidate(key);
    }

    /**
     * 移除所有缓存
     */
    public static void removeAll() {
        cache.invalidateAll();
    }

    /**
     * 获取缓存条目
     *
     * @return
     */
    public static long getSize() {
        return cache.size();
    }
}
