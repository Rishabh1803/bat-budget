package com.rk.batbudget.cacheservice.service;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheService {

    private final RedisCacheManager cacheManager;

    public CacheService(RedisCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public <K, V> void put(String cacheName, K key, V value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    public <K, V> void putIfAbsent(String cacheName, K key, V value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.putIfAbsent(key, value);
        }
    }

    public <K, V> Optional<V> get(String cacheName, K key, Class<V> type) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(key);
            if (valueWrapper != null) {
                Object object = valueWrapper.get();
                if (type.isInstance(object)) {
                    return Optional.of(type.cast(object));
                }
            }
        }
        return Optional.empty();
    }

    public <K> void evict(String cacheName, K key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evictIfPresent(key);
        }
    }

    public void clear(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }

}
