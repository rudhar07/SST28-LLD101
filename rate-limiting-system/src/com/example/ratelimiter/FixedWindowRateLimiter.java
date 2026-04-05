package com.example.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {
    private final RateLimiterConfig config;
    private final ConcurrentHashMap<String, WindowCounter> counters;

    public FixedWindowRateLimiter(RateLimiterConfig config) {
        this.config = config;
        this.counters = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        long windowStart = (now / config.getWindowSizeMillis()) * config.getWindowSizeMillis();

        WindowCounter counter = counters.get(key);

        if (counter == null || counter.windowStart != windowStart) {
            counter = new WindowCounter(windowStart);
            counters.put(key, counter);
        }

        if (counter.count.get() < config.getMaxRequests()) {
            counter.count.incrementAndGet();
            return true;
        }

        return false;
    }

    private static class WindowCounter {
        final long windowStart;
        final AtomicInteger count;

        WindowCounter(long windowStart) {
            this.windowStart = windowStart;
            this.count = new AtomicInteger(0);
        }
    }

    @Override
    public String toString() {
        return "FixedWindowRateLimiter [" + config + "]";
    }
}
