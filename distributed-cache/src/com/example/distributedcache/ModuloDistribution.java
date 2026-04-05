package com.example.distributedcache;

public class ModuloDistribution implements DistributionStrategy {

    @Override
    public int getNodeIndex(String key, int totalNodes) {
        int hash = Math.abs(key.hashCode());
        return hash % totalNodes;
    }
}
