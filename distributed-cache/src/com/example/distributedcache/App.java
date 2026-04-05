package com.example.distributedcache;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Initializing Distributed Cache System ===\n");

        InMemoryDatabase database = new InMemoryDatabase();
        database.put("user:1", "Alice");
        database.put("user:2", "Bob");
        database.put("user:3", "Charlie");
        database.put("user:4", "Diana");
        database.put("user:5", "Eve");
        System.out.println("Database pre-loaded with 5 users.\n");

        // 3 nodes, capacity 2 each, LRU eviction, modulo distribution
        DistributedCache cache = new DistributedCache(
                3,
                2,
                new ModuloDistribution(),
                database
        );

        cache.status();

        // cache miss - key not in cache, fetched from DB
        System.out.println("\n=== Scenario 1: Cache Miss (fetch from DB) ===");
        cache.get("user:1");
        cache.get("user:2");
        cache.get("user:3");

        cache.status();

        // cache hit
        System.out.println("\n=== Scenario 2: Cache Hit ===");
        cache.get("user:1");
        cache.get("user:2");

        // put new data
        System.out.println("\n=== Scenario 3: Put new data ===");
        cache.put("user:6", "Frank");
        cache.put("user:7", "Grace");

        cache.status();

        // eviction - node is full, LRU key gets evicted
        System.out.println("\n=== Scenario 4: LRU Eviction ===");
        cache.put("user:8", "Hank");
        cache.put("user:9", "Ivy");
        cache.put("user:10", "Jack");

        cache.status();

        // key not in DB
        System.out.println("\n=== Scenario 5: Key not in DB ===");
        cache.get("user:999");

        // re-access evicted key
        System.out.println("\n=== Scenario 6: Re-access evicted key ===");
        cache.get("user:1");

        cache.status();

        // update existing key
        System.out.println("\n=== Scenario 7: Update existing key ===");
        cache.put("user:6", "Franklin");
        cache.get("user:6");

        cache.status();
    }
}
