package com.jtech.nanonlimiter.service;

public interface RateLimiter {
    boolean allowRequest();
}
