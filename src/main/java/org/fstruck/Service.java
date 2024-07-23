package org.fstruck;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class Service {

    private final Map<Long, Long> cache = new HashMap<>();

    public Service() {
        // Initialize
        cache.put(0L, 0L);
        cache.put(1L, 1L);
        cache.put(2L, 0L);
        cache.put(3L, 1L);
    }

    public long getValue(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("The number can't be negative.");
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long value = getValue(n - 4) + getValue(n - 3);
        cache.put(n, value);
        return value;
    }

    public long[] getCicleValue(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The number can't be negative.");
        }

        long[] sequence = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            sequence[i] = getValue(i);
        }
        return sequence;
    }
}
