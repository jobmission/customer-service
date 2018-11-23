package com.revengemission.customerservice.config;

import com.revengemission.customerservice.sessionstore.MemorySessionRepository;
import com.revengemission.customerservice.sessionstore.MemorySessionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


@EnableScheduling
@EnableSpringHttpSession
@Configuration
@ConditionalOnProperty(value = "spring.session.store-type", havingValue = "none")
public class SpringHttpSessionConfig {

    @Value("${spring.session.timeout:1800s}")
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration timeout;

    @Autowired
    MemorySessionStore memorySessionStore;

    @Bean
    public MemorySessionRepository sessionRepository() {
        return new MemorySessionRepository(memorySessionStore, timeout);
    }

}


