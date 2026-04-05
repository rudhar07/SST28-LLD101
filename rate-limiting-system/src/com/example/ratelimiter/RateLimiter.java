package com.example.ratelimiter;

/**
 * Core interface for rate limiting.
 * Any rate limiting algorithm (Fixed Window, Sliding Window, Token Bucket, etc.)
 * must implement this interface.
 */
public interface RateLimiter {
    /**
     * Checks if a request identified by the given key is allowed.
     * @param key the rate limiting key (e.g., customer ID, tenant ID, API key)
     * @return true if the request is allowed, false if rate limit exceeded
     */
    boolean allowRequest(String key);
}
