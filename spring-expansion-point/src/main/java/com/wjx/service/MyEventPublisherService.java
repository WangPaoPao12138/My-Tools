package com.wjx.service;

import com.wjx.task.event.Event1;
import com.wjx.task.event.Event2;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Gin
 * @description
 * @date 2024/1/11 17:40
 */
@Service
public class MyEventPublisherService {
    @Resource
    private ApplicationContext applicationContext;

    public void publishEvent1() {
        applicationContext.publishEvent(new Event1("event1"));
    }
    public void publishEvent2() {
        applicationContext.publishEvent(new Event2("event2"));
    }
}
