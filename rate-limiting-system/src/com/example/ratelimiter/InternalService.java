package com.example.ratelimiter;

/**
 * Internal service that processes client requests.
 * Business logic determines whether an external call is needed.
 * If yes, the ExternalResourceGateway (with rate limiting) is consulted.
 */
public class InternalService {
    private final ExternalResourceGateway gateway;

    public InternalService(ExternalResourceGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * Processes a client request.
     * Rate limiting is applied ONLY when the request needs an external call.
     */
    public String process(ClientRequest request) {
        System.out.println("\n-> Processing " + request);

        // Step 1: Run business logic
        System.out.println("   [Service] Running business logic...");

        // Step 2: Check if external call is needed
        if (!request.needsExternalCall()) {
            String result = "internal_result_for_" + request.getData();
            System.out.println("   [Service] No external call needed. Result: \"" + result + "\"");
            return result;
        }

        // Step 3: External call needed — gateway handles rate limiting
        System.out.println("   [Service] External call required — consulting rate limiter...");
        String externalResult = gateway.callExternalResource(
                request.getClientId(),
                request.getData()
        );

        if (externalResult == null) {
            System.out.println("   [Service] Request REJECTED — rate limit exceeded");
            return null;
        }

        System.out.println("   [Service] Request completed. Result: \"" + externalResult + "\"");
        return externalResult;
    }
}
