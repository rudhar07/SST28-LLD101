package com.example.distributedcache;

import java.util.Deque;
import java.util.LinkedList;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
    private Deque<K> accessOrder;

    public LRUEvictionPolicy() {
        this.accessOrder = new LinkedList<>();
    }

    @Override
    public void keyAccessed(K key) {
        accessOrder.remove(key);
        accessOrder.addFirst(key);
    }

    @Override
    public K evictKey() {
        if (accessOrder.isEmpty()) return null;
        return accessOrder.removeLast();
    }

    @Override
    public void removeKey(K key) {
        accessOrder.remove(key);
    }
}
