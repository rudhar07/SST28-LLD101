package com.example.distributedcache;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Initializing Distributed Cache System ===\n");

        // Setup: database with some pre-existing data
        InMemoryDatabase database = new InMemoryDatabase();
        database.put("user:1", "Alice");
        database.put("user:2", "Bob");
        database.put("user:3", "Charlie");
        database.put("user:4", "Diana");
        database.put("user:5", "Eve");
        System.out.println("Database pre-loaded with 5 users.\n");

        // Create cache: 3 nodes, capacity 2 each, LRU eviction, modulo distribution
        DistributedCache cache = new DistributedCache(
                3,                          // 3 cache nodes
                2,                          // capacity 2 per node
                new ModuloDistribution(),   // modulo-based distribution
                database
        );

        cache.status();

        // --- Scenario 1: Cache miss (key not in cache, fetched from DB) ---
        System.out.println("\n=== Scenario 1: Cache Miss (fetch from DB) ===");
        cache.get("user:1");  // Miss -> fetch from DB -> store in cache
        cache.get("user:2");  // Miss -> fetch from DB -> store in cache
        cache.get("user:3");  // Miss -> fetch from DB -> store in cache

        cache.status();

        // --- Scenario 2: Cache hit (key already in cache) ---
        System.out.println("\n=== Scenario 2: Cache Hit ===");
        cache.get("user:1");  // Should be a HIT
        cache.get("user:2");  // Should be a HIT

        // --- Scenario 3: Put new data (stored in cache + DB) ---
        System.out.println("\n=== Scenario 3: Put new data ===");
        cache.put("user:6", "Frank");
        cache.put("user:7", "Grace");

        cache.status();

        // --- Scenario 4: Eviction (cache node is full, LRU key evicted) ---
        System.out.println("\n=== Scenario 4: LRU Eviction ===");
        // Adding more keys to trigger eviction on nodes that are full
        cache.put("user:8", "Hank");
        cache.put("user:9", "Ivy");
        cache.put("user:10", "Jack");

        cache.status();

        // --- Scenario 5: Cache miss for key not in DB ---
        System.out.println("\n=== Scenario 5: Key not in DB ===");
        cache.get("user:999");  // Not in cache, not in DB

        // --- Scenario 6: Re-access evicted key (fetched again from DB) ---
        System.out.println("\n=== Scenario 6: Re-access evicted key ===");
        cache.get("user:1");  // Was likely evicted, should be a MISS -> fetch from DB

        cache.status();

        // --- Scenario 7: Update existing key ---
        System.out.println("\n=== Scenario 7: Update existing key ===");
        cache.put("user:6", "Franklin");  // Update Frank -> Franklin
        cache.get("user:6");              // Should return updated value

        cache.status();
    }
}
