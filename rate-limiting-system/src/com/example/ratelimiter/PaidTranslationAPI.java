package com.example.ratelimiter;

/**
 * A simulated paid translation API.
 * Each call costs money, hence the need for rate limiting.
 */
public class PaidTranslationAPI implements ExternalResource {

    @Override
    public String call(String requestData) {
        // Simulate external API call
        System.out.println("      [ExternalAPI] Translating: \"" + requestData + "\"");
        return "translated_" + requestData;
    }
}
