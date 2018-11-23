package com.revengemission.customerservice.sessionstore;

import org.springframework.session.MapSession;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemorySessionStore {
    private final Map<String, MapSession> sessions;

    public MemorySessionStore() {
        sessions = new ConcurrentHashMap<>(4096);
    }

    public Map<String, MapSession> getSessions() {
        return sessions;
    }
}
