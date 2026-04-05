package com.example.ratelimiter;

public class InternalService {
    private final ExternalResourceGateway gateway;

    public InternalService(ExternalResourceGateway gateway) {
        this.gateway = gateway;
    }

    public String process(ClientRequest request) {
        System.out.println("\n-> Processing " + request);
        System.out.println("   [Service] Running business logic...");

        if (!request.needsExternalCall()) {
            String result = "internal_result_for_" + request.getData();
            System.out.println("   [Service] No external call needed. Result: \"" + result + "\"");
            return result;
        }

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
