package com.example.distributedcache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private int nodeId;
    private int capacity;
    private Map<String, String> store;
    private EvictionPolicy<String> evictionPolicy;

    public CacheNode(int nodeId, int capacity, EvictionPolicy<String> evictionPolicy) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.store = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public String get(String key) {
        if (!store.containsKey(key)) {
            return null;
        }
        evictionPolicy.keyAccessed(key);
        return store.get(key);
    }

    public void put(String key, String value) {
        if (store.containsKey(key)) {
            store.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        if (store.size() >= capacity) {
            String evictedKey = evictionPolicy.evictKey();
            if (evictedKey != null) {
                store.remove(evictedKey);
                System.out.println("   [Node " + nodeId + "] Evicted key: \"" + evictedKey + "\"");
            }
        }

        store.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    public boolean containsKey(String key) {
        return store.containsKey(key);
    }

    public int getNodeId() { return nodeId; }
    public int getCapacity() { return capacity; }
    public int getCurrentSize() { return store.size(); }

    public String getStatus() {
        return "Node " + nodeId + " [" + store.size() + "/" + capacity + "] keys=" + store.keySet();
    }
}
