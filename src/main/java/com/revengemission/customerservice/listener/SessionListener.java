package com.revengemission.customerservice.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("sessionCreated");
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("sessionDestroyed");
    }
}
