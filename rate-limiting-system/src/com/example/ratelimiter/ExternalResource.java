package com.example.ratelimiter;

/**
 * Represents a paid external resource/API.
 * Each call to this resource incurs a cost.
 */
public interface ExternalResource {
    /**
     * Calls the external resource with the given request data.
     * @param requestData the data to send to the external API
     * @return the response from the external API
     */
    String call(String requestData);
}
