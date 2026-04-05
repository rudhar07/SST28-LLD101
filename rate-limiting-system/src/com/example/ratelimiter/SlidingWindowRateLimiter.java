package com.example.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Sliding Window Counter rate limiter.
 *
 * Maintains a deque of timestamps for each key.
 * On each request, removes timestamps older than the window,
 * then checks if the remaining count is within the limit.
 *
 * Trade-offs:
 *   + Smooth rate limiting — no boundary burst problem
 *   + More accurate than Fixed Window
 *   - Higher memory usage (stores individual timestamps)
 *   - O(n) cleanup on each check (n = expired timestamps)
 */
public class SlidingWindowRateLimiter implements RateLimiter {
    private final RateLimiterConfig config;

    // Maps key -> deque of request timestamps
    private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Long>> requestLogs;

    public SlidingWindowRateLimiter(RateLimiterConfig config) {
        this.config = config;
        this.requestLogs = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        long windowStart = now - config.getWindowSizeMillis();

        ConcurrentLinkedDeque<Long> timestamps = requestLogs
                .computeIfAbsent(key, k -> new ConcurrentLinkedDeque<>());

        // Remove timestamps outside the current window
        while (!timestamps.isEmpty() && timestamps.peekFirst() <= windowStart) {
            timestamps.pollFirst();
        }

        if (timestamps.size() < config.getMaxRequests()) {
            timestamps.addLast(now);
            return true;
        }

        return false; // rate limit exceeded
    }

    @Override
    public String toString() {
        return "SlidingWindowRateLimiter [" + config + "]";
    }
}
