package com.example.ratelimiter;

public class ExternalResourceGateway {
    private final ExternalResource externalResource;
    private RateLimiter rateLimiter;

    public ExternalResourceGateway(ExternalResource externalResource, RateLimiter rateLimiter) {
        this.externalResource = externalResource;
        this.rateLimiter = rateLimiter;
    }

    public String callExternalResource(String key, String requestData) {
        if (!rateLimiter.allowRequest(key)) {
            System.out.println("   [Gateway] DENIED for key=\"" + key
                    + "\" — rate limit exceeded (" + rateLimiter + ")");
            return null;
        }

        System.out.println("   [Gateway] ALLOWED for key=\"" + key + "\"");
        return externalResource.call(requestData);
    }

    public void setRateLimiter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
        System.out.println("   [Gateway] Rate limiter switched to: " + rateLimiter);
    }

    public RateLimiter getRateLimiter() { return rateLimiter; }
}
