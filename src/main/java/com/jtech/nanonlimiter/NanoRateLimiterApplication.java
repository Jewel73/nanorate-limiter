package com.jtech.nanonlimiter;

import com.jtech.nanonlimiter.config.RateLimitConfig;
import com.jtech.nanonlimiter.factory.RateLimiterFactory;
import com.jtech.nanonlimiter.service.RateLimiter;
import com.jtech.nanonlimiter.service.impl.RateLimiterManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NanoRateLimiterApplication {
    public static void main(String[] args) throws InterruptedException {

        RateLimiterManager rateLimiterManager = new RateLimiterManager();
        RateLimitConfig rateLimitConfigApp1 = new RateLimitConfig(1, 2);
        RateLimiter rateLimiterApp1 = RateLimiterFactory.createTokenBucketLimiter(rateLimitConfigApp1);

        RateLimitConfig rateLimitConfigApp2 = new RateLimitConfig(20, 2);
        RateLimiter rateLimiterApp2 = RateLimiterFactory.createTokenBucketLimiter(rateLimitConfigApp2);

        rateLimiterManager.registerLimiter("app1", rateLimiterApp1);
        rateLimiterManager.registerLimiter("app2", rateLimiterApp2);

        for (int i = 0; i < 10 ; i++){
            System.out.println("App1 : Allow request : " + rateLimiterManager.allowRequest("app1"));
        }

        for (int i = 0; i < 10 ; i++){
            System.out.println("App2 : Allow request : " + rateLimiterManager.allowRequest("app2"));
        }
    }
}
