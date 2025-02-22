package com.jtech.nanonlimiter.service.impl;

import com.jtech.nanonlimiter.config.RateLimitConfig;
import com.jtech.nanonlimiter.service.RateLimiter;

public class LeakyBucketLimiter implements RateLimiter {

    public LeakyBucketLimiter(RateLimitConfig rateLimitConfig){

    }

    @Override
    public boolean allowRequest() {
        return false;
    }
}

