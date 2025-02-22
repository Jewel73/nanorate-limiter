package com.jtech.nanonlimiter.service.impl;

import com.jtech.nanonlimiter.service.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterManager {
    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    public void registerLimiter(String key, RateLimiter rateLimiter){
        rateLimiterMap.put(key, rateLimiter);
    }

    public boolean allowRequest(String key){
        RateLimiter rateLimiter = rateLimiterMap.get(key);
        if (rateLimiter == null){
            throw new IllegalArgumentException("No Limiter registered for the key: "+ key);
        }
        return rateLimiter.allowRequest();
    }
}
