package com.wjx.task.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Gin
 * @description
 * @date 2024/1/11 17:37
 */
public class Event2 extends ApplicationEvent {
    public Event2(Object source) {
        super(source);
    }
}
