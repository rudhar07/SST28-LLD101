package com.example.ratelimiter;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Initializing Rate Limiting System ===\n");

        ExternalResource translationAPI = new PaidTranslationAPI();

        // 5 requests per 3 second window
        RateLimiterConfig config = new RateLimiterConfig(5, 3000);
        System.out.println("Rate limit configured: " + config + "\n");

        RateLimiter fixedWindow = new FixedWindowRateLimiter(config);
        ExternalResourceGateway gateway = new ExternalResourceGateway(translationAPI, fixedWindow);
        InternalService service = new InternalService(gateway);

        // no external call needed - rate limiter not consulted
        System.out.println("=== Scenario 1: No external call needed ===");
        service.process(new ClientRequest("T1", "local-data-1", false));
        service.process(new ClientRequest("T1", "local-data-2", false));
        service.process(new ClientRequest("T1", "local-data-3", false));

        // fixed window - requests within limit
        System.out.println("\n\n=== Scenario 2: Fixed Window — within limit (5 allowed) ===");
        System.out.println("Using: " + fixedWindow);
        for (int i = 1; i <= 5; i++) {
            service.process(new ClientRequest("T1", "translate-" + i, true));
        }

        // exceeding limit
        System.out.println("\n\n=== Scenario 3: Fixed Window — exceeding limit ===");
        service.process(new ClientRequest("T1", "translate-6", true));
        service.process(new ClientRequest("T1", "translate-7", true));

        // different keys have separate limits
        System.out.println("\n\n=== Scenario 4: Per-key isolation (T2 has its own quota) ===");
        service.process(new ClientRequest("T2", "translate-A", true));
        service.process(new ClientRequest("T2", "translate-B", true));

        // wait for window to reset
        System.out.println("\n\n=== Scenario 5: Window reset (waiting 3 seconds...) ===");
        Thread.sleep(3100);
        service.process(new ClientRequest("T1", "translate-after-reset", true));

        // switch to sliding window at runtime
        System.out.println("\n\n=== Scenario 6: Hot-swap to Sliding Window algorithm ===");
        RateLimiter slidingWindow = new SlidingWindowRateLimiter(config);
        gateway.setRateLimiter(slidingWindow);

        System.out.println("Using: " + slidingWindow);
        for (int i = 1; i <= 7; i++) {
            service.process(new ClientRequest("T1", "sliding-" + i, true));
        }

        // sliding window smooth recovery
        System.out.println("\n\n=== Scenario 7: Sliding Window — smooth recovery after wait ===");
        Thread.sleep(3100);
        service.process(new ClientRequest("T1", "sliding-after-wait", true));

        System.out.println("\n\n=== Rate Limiting System Demo Complete ===");
    }
}
