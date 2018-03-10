package com.revengemission.customerservice.domain;

import org.springframework.context.ApplicationEvent;

public class EventData extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String eventName;


    public EventData(Object source, String userId, String eventName) {
        super(source);
        this.userId = userId;
        this.eventName = eventName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
