package com.ldb.truck.Service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private static final int MAX_REQUESTS_PER_MINUTE = 10;
    private final Map<String, RequestTracker> trackingMap = new ConcurrentHashMap<>();

    public boolean isAllowed(String clientIp) {
        if (clientIp == null || clientIp.trim().isEmpty()) {
            return true;
        }

        long now = System.currentTimeMillis();
        // Clean up entries older than 1 minute
        trackingMap.entrySet().removeIf(entry -> now - entry.getValue().firstRequestTime > 60000);

        RequestTracker tracker = trackingMap.computeIfAbsent(clientIp, k -> new RequestTracker(now));
        synchronized (tracker) {
            if (now - tracker.firstRequestTime > 60000) {
                tracker.firstRequestTime = now;
                tracker.count = 1;
                return true;
            }
            if (tracker.count >= MAX_REQUESTS_PER_MINUTE) {
                return false;
            }
            tracker.count++;
            return true;
        }
    }

    private static class RequestTracker {
        long firstRequestTime;
        int count;

        RequestTracker(long firstRequestTime) {
            this.firstRequestTime = firstRequestTime;
            this.count = 1;
        }
    }
}
