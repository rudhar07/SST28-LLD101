package com.example.ratelimiter;

public class PaidTranslationAPI implements ExternalResource {

    @Override
    public String call(String requestData) {
        System.out.println("      [ExternalAPI] Translating: \"" + requestData + "\"");
        return "translated_" + requestData;
    }
}
