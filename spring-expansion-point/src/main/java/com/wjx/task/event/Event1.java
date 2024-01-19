package com.wjx.task.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Gin
 * @description
 * @date 2024/1/11 17:36
 */
public class Event1 extends ApplicationEvent {
    public Event1(Object source) {
        super(source);
    }
}
