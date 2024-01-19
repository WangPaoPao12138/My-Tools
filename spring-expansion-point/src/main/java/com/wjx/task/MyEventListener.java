package com.wjx.task;

import com.wjx.task.event.Event1;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Gin
 * @description
 * @date 2024/1/11 17:34
 */
@Component
public class MyEventListener {

    @EventListener
    //根据入参类型event
    public void listenEvent1(Event1 event1) {
        System.out.println("event："+event1.getSource().toString());
    }
}
