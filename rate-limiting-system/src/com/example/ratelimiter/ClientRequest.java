package com.example.ratelimiter;

public class ClientRequest {
    private final String clientId;
    private final String data;
    private final boolean needsExternalCall;

    public ClientRequest(String clientId, String data, boolean needsExternalCall) {
        this.clientId = clientId;
        this.data = data;
        this.needsExternalCall = needsExternalCall;
    }

    public String getClientId() { return clientId; }
    public String getData() { return data; }
    public boolean needsExternalCall() { return needsExternalCall; }

    @Override
    public String toString() {
        return "Request{client=\"" + clientId + "\", data=\"" + data
                + "\", external=" + needsExternalCall + "}";
    }
}
