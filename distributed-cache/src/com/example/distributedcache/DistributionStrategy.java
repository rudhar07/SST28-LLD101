package com.example.distributedcache;

public interface DistributionStrategy {
    int getNodeIndex(String key, int totalNodes);
}
