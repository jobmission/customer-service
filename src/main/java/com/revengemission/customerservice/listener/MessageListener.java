package com.revengemission.customerservice.listener;

import com.revengemission.customerservice.domain.EventData;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
//这里注意我们直接把监听类注册成组件,如果不指定泛型，监听所有事件，需要在onApplicationEvent中if else
public class MessageListener implements ApplicationListener<EventData> {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Async
    @Override
    public void onApplicationEvent(EventData event) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Event name:" + event.getEventName());
    }
}
