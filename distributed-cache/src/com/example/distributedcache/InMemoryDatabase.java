package com.example.distributedcache;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase implements Database {
    private Map<String, String> store;

    public InMemoryDatabase() {
        this.store = new HashMap<>();
    }

    @Override
    public String get(String key) {
        return store.get(key);
    }

    @Override
    public void put(String key, String value) {
        store.put(key, value);
    }

    public int size() {
        return store.size();
    }
}
