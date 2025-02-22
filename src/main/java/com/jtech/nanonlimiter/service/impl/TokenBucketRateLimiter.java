package com.jtech.nanonlimiter.service.impl;

import com.jtech.nanonlimiter.config.RateLimitConfig;
import com.jtech.nanonlimiter.service.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int maxTokens;
    private final long refillIntervalMillis;
    private final AtomicInteger tokens;
    private final AtomicLong lastRefillIntervalMillis;

    public TokenBucketRateLimiter(RateLimitConfig rateLimitConfig){
        this.maxTokens = rateLimitConfig.getMaxRequests();
        this.refillIntervalMillis = rateLimitConfig.getTimeWindowMillis();
        this.tokens = new AtomicInteger(maxTokens);
        this.lastRefillIntervalMillis = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public boolean allowRequest() {
        refillTokens();
        int currentTokens = tokens.get();
        if (currentTokens > 0 &&  tokens.compareAndSet(currentTokens, currentTokens-1)){
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        if (now - lastRefillIntervalMillis.get() >= refillIntervalMillis){
            synchronized (this){
                if (now - lastRefillIntervalMillis.get() >= refillIntervalMillis){
                    tokens.set(maxTokens);
                    lastRefillIntervalMillis.set(now);
                }
            }
        }
    }
}
