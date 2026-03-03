package com.example.metrics;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * Implemented as a lazy, thread-safe Singleton that is safe against
 * reflection and preserves the singleton guarantee across serialization.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Guard used to prevent multiple construction attempts (including via reflection).
    private static boolean constructed = false;

    private final Map<String, Long> counters = new HashMap<>();

    private MetricsRegistry() {
        // Synchronize on the class object so that even reflective construction
        // cannot create more than one instance safely.
        synchronized (MetricsRegistry.class) {
            if (constructed) {
                throw new IllegalStateException("MetricsRegistry singleton already constructed");
            }
            constructed = true;
        }
    }

    /**
     * Lazy, thread-safe singleton using the Initialization-on-demand holder idiom.
     */
    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    /**
     * Ensure that deserialization does not create a new instance.
     */
    @Serial
    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
}
