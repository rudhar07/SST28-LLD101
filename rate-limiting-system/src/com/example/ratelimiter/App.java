package com.example.ratelimiter;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Initializing Rate Limiting System ===\n");

        // Setup: paid external resource
        ExternalResource translationAPI = new PaidTranslationAPI();

        // Config: 5 requests per 3000ms (3-second window for demo purposes)
        RateLimiterConfig config = new RateLimiterConfig(5, 3000);
        System.out.println("Rate limit configured: " + config + "\n");

        // Start with Fixed Window algorithm
        RateLimiter fixedWindow = new FixedWindowRateLimiter(config);
        ExternalResourceGateway gateway = new ExternalResourceGateway(translationAPI, fixedWindow);
        InternalService service = new InternalService(gateway);

        // --- Scenario 1: Requests that do NOT need external calls (no rate limiting) ---
        System.out.println("=== Scenario 1: No external call needed (rate limiter not consulted) ===");
        service.process(new ClientRequest("T1", "local-data-1", false));
        service.process(new ClientRequest("T1", "local-data-2", false));
        service.process(new ClientRequest("T1", "local-data-3", false));

        // --- Scenario 2: Fixed Window — requests within limit ---
        System.out.println("\n\n=== Scenario 2: Fixed Window — within limit (5 allowed) ===");
        System.out.println("Using: " + fixedWindow);
        for (int i = 1; i <= 5; i++) {
            service.process(new ClientRequest("T1", "translate-" + i, true));
        }

        // --- Scenario 3: Fixed Window — exceeding limit ---
        System.out.println("\n\n=== Scenario 3: Fixed Window — exceeding limit ===");
        service.process(new ClientRequest("T1", "translate-6", true));  // DENIED
        service.process(new ClientRequest("T1", "translate-7", true));  // DENIED

        // --- Scenario 4: Different keys have separate limits ---
        System.out.println("\n\n=== Scenario 4: Per-key isolation (T2 has its own quota) ===");
        service.process(new ClientRequest("T2", "translate-A", true));  // ALLOWED (T2's first)
        service.process(new ClientRequest("T2", "translate-B", true));  // ALLOWED (T2's second)

        // --- Scenario 5: Wait for window to reset, then requests are allowed again ---
        System.out.println("\n\n=== Scenario 5: Window reset (waiting 3 seconds...) ===");
        Thread.sleep(3100);  // Wait for the window to reset
        service.process(new ClientRequest("T1", "translate-after-reset", true));  // ALLOWED

        // --- Scenario 6: Switch to Sliding Window algorithm at runtime ---
        System.out.println("\n\n=== Scenario 6: Hot-swap to Sliding Window algorithm ===");
        RateLimiter slidingWindow = new SlidingWindowRateLimiter(config);
        gateway.setRateLimiter(slidingWindow);

        System.out.println("Using: " + slidingWindow);
        for (int i = 1; i <= 7; i++) {
            service.process(new ClientRequest("T1", "sliding-" + i, true));
        }
        // First 5 ALLOWED, last 2 DENIED

        // --- Scenario 7: Sliding Window — smooth recovery ---
        System.out.println("\n\n=== Scenario 7: Sliding Window — smooth recovery after wait ===");
        Thread.sleep(3100);
        service.process(new ClientRequest("T1", "sliding-after-wait", true));  // ALLOWED

        System.out.println("\n\n=== Rate Limiting System Demo Complete ===");
    }
}
