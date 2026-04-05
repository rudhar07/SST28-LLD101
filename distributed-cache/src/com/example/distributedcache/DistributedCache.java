package com.example.distributedcache;

import java.util.ArrayList;
import java.util.List;

public class DistributedCache {
    private List<CacheNode> nodes;
    private DistributionStrategy distributionStrategy;
    private Database database;

    public DistributedCache(int numberOfNodes, int capacityPerNode,
                            DistributionStrategy distributionStrategy,
                            Database database) {
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        this.nodes = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            // Each node gets its own eviction policy instance
            EvictionPolicy<String> policy = new LRUEvictionPolicy<>();
            nodes.add(new CacheNode(i, capacityPerNode, policy));
        }

        System.out.println("Distributed Cache initialized: " + numberOfNodes
                + " nodes, capacity " + capacityPerNode + " per node");
    }

    public String get(String key) {
        int nodeIndex = distributionStrategy.getNodeIndex(key, nodes.size());
        CacheNode node = nodes.get(nodeIndex);

        String value = node.get(key);
        if (value != null) {
            // Cache hit
            System.out.println("-> GET \"" + key + "\" = \"" + value + "\" [CACHE HIT from Node " + nodeIndex + "]");
            return value;
        }

        // Cache miss — fetch from database
        value = database.get(key);
        if (value != null) {
            node.put(key, value);
            System.out.println("-> GET \"" + key + "\" = \"" + value + "\" [CACHE MISS -> fetched from DB, stored in Node " + nodeIndex + "]");
        } else {
            System.out.println("-> GET \"" + key + "\" = null [NOT FOUND in DB]");
        }

        return value;
    }

    public void put(String key, String value) {
        int nodeIndex = distributionStrategy.getNodeIndex(key, nodes.size());
        CacheNode node = nodes.get(nodeIndex);

        node.put(key, value);
        database.put(key, value);
        System.out.println("-> PUT \"" + key + "\" = \"" + value + "\" [Stored in Node " + nodeIndex + " + DB]");
    }

    public void status() {
        System.out.println("\n--- Distributed Cache Status ---");
        for (CacheNode node : nodes) {
            System.out.println("   " + node.getStatus());
        }
    }

    public List<CacheNode> getNodes() { return nodes; }
}
