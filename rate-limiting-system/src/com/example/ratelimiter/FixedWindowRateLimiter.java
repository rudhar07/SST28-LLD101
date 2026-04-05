package com.example.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fixed Window Counter rate limiter.
 *
 * Divides time into fixed-size windows (e.g., every 60 seconds).
 * Each key gets a counter that resets at the start of each window.
 *
 * Trade-offs:
 *   + Simple and memory-efficient (one counter per key per window)
 *   + O(1) time per check
 *   - Boundary problem: burst of requests at the end of one window
 *     and start of the next can allow 2x the limit in a short span
 */
public class FixedWindowRateLimiter implements RateLimiter {
    private final RateLimiterConfig config;

    // Maps key -> WindowCounter
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

        // Reset counter if we're in a new window
        if (counter == null || counter.windowStart != windowStart) {
            counter = new WindowCounter(windowStart);
            counters.put(key, counter);
        }

        if (counter.count.get() < config.getMaxRequests()) {
            counter.count.incrementAndGet();
            return true;
        }

        return false; // rate limit exceeded
    }

    /**
     * Tracks count within a single time window.
     */
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
