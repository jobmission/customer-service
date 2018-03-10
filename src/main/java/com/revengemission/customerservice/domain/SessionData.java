package com.revengemission.customerservice.domain;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData implements Serializable {

    private String sessionId;
    private String ip;
    private Object data;
    private String token;
    private String refreshToken;
    private String roleName;
    private String userName;
    private Long timestamp;

}
