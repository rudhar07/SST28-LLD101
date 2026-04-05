package com.example.ratelimiter;

/**
 * Configuration for rate limiting.
 * Defines the maximum number of requests allowed within a time window.
 */
public class RateLimiterConfig {
    private final int maxRequests;
    private final long windowSizeMillis;

    public RateLimiterConfig(int maxRequests, long windowSizeMillis) {
        if (maxRequests <= 0) throw new IllegalArgumentException("maxRequests must be positive");
        if (windowSizeMillis <= 0) throw new IllegalArgumentException("windowSizeMillis must be positive");
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public int getMaxRequests() { return maxRequests; }
    public long getWindowSizeMillis() { return windowSizeMillis; }

    @Override
    public String toString() {
        return maxRequests + " requests per " + windowSizeMillis + "ms";
    }
}
