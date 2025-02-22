package com.jtech.nanonlimiter.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateLimitConfig {
    private final int maxRequests;
    private final long timeWindowMillis;

    public RateLimitConfig(int maxRequests, long timeWindowMillis){
        this.maxRequests = maxRequests;
        this.timeWindowMillis = timeWindowMillis;
    }
}
