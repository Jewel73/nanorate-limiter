package com.jtech.nanonlimiter.factory;

import com.jtech.nanonlimiter.config.RateLimitConfig;
import com.jtech.nanonlimiter.service.RateLimiter;
import com.jtech.nanonlimiter.service.impl.LeakyBucketLimiter;
import com.jtech.nanonlimiter.service.impl.TokenBucketRateLimiter;

public class RateLimiterFactory {

    public static RateLimiter createTokenBucketLimiter(RateLimitConfig rateLimitConfig){
        return new TokenBucketRateLimiter(rateLimitConfig);
    }

    public static RateLimiter createLeakyBucketLimiter(RateLimitConfig rateLimitConfig){
        return new LeakyBucketLimiter(rateLimitConfig);
    }
}
