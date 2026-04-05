package com.example.ratelimiter;

/**
 * Gateway that guards access to a paid external resource.
 * Consults the rate limiter ONLY when an external call is about to be made.
 *
 * The rate limiter can be swapped at runtime without changing business logic.
 */
public class ExternalResourceGateway {
    private final ExternalResource externalResource;
    private RateLimiter rateLimiter;

    public ExternalResourceGateway(ExternalResource externalResource, RateLimiter rateLimiter) {
        this.externalResource = externalResource;
        this.rateLimiter = rateLimiter;
    }

    /**
     * Attempts to call the external resource for the given key.
     * The rate limiter is checked first — if the limit is exceeded,
     * the call is denied and null is returned.
     *
     * @param key         the rate limiting key (customer, tenant, API key, etc.)
     * @param requestData the data to send to the external resource
     * @return the response from the external resource, or null if rate-limited
     */
    public String callExternalResource(String key, String requestData) {
        if (!rateLimiter.allowRequest(key)) {
            System.out.println("   [Gateway] DENIED for key=\"" + key
                    + "\" — rate limit exceeded (" + rateLimiter + ")");
            return null;
        }

        System.out.println("   [Gateway] ALLOWED for key=\"" + key + "\"");
        return externalResource.call(requestData);
    }

    /**
     * Swap the rate limiting algorithm at runtime.
     * Business logic (callers) need not change.
     */
    public void setRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
        System.out.println("   [Gateway] Rate limiter switched to: " + rateLimiter);
    }

    public RateLimiter getRateLimiter() { return rateLimiter; }
}
