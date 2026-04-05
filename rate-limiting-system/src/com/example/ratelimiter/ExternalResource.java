package com.example.ratelimiter;

public interface ExternalResource {
    String call(String requestData);
}
