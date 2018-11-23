package com.revengemission.customerservice.sessionstore;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.MapSession;
import org.springframework.session.SessionRepository;

import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class MemorySessionRepository implements SessionRepository<MapSession> {

    private Duration timeout;
    MemorySessionStore memorySessionStore;

    public MemorySessionRepository(MemorySessionStore memorySessionStore) {
        this.memorySessionStore = memorySessionStore;
        this.timeout = Duration.ofSeconds(1800);
    }

    public MemorySessionRepository(MemorySessionStore memorySessionStore, Duration timeout) {
        this.timeout = timeout;
        this.memorySessionStore = memorySessionStore;
    }


    @Override
    public MapSession createSession() {
        MapSession result = new MapSession();
        result.setMaxInactiveInterval(timeout);
        return result;
    }

    @Override
    public void save(MapSession session) {
        memorySessionStore.getSessions().put(session.getId(), new MapSession(session));
    }

    @Override
    public MapSession findById(String id) {
        MapSession saved = memorySessionStore.getSessions().get(id);
        if (saved == null) {
            return null;
        }
        if (saved.isExpired()) {
            deleteById(saved.getId());
            return null;
        }
        return new MapSession(saved);
    }

    @Override
    public void deleteById(String id) {
        memorySessionStore.getSessions().remove(id);
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void cleanUpExpiredSessions() {
        System.out.println("cleanUpExpiredSessions...begin " + new Date());
        System.out.println("total sessions:" + memorySessionStore.getSessions().size());
        Iterator<Map.Entry<String, MapSession>> it = memorySessionStore.getSessions().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, MapSession> entry = it.next();
            MapSession entryValue = entry.getValue();
            if ((System.currentTimeMillis() - entryValue.getLastAccessedTime().toEpochMilli()) / 1000 > entryValue.getMaxInactiveInterval().getSeconds()) {
                System.out.println("cleanUpExpiredSessions: delete this " + entry.getKey());
                it.remove();        //OK
            }
        }
        System.out.println("cleanUpExpiredSessions...end");
    }
}
