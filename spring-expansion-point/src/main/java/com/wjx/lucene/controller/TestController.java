package com.wjx.lucene.controller;

import com.wjx.service.MyEventPublisherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Gin
 * @description
 * @date 2024/1/11 17:24
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    MyEventPublisherService publisherService;

    @RequestMapping("event1")
    public void event1() {
        publisherService.publishEvent1();
    }

    @RequestMapping("event2")
    public void event2() {
        publisherService.publishEvent2();
    }
}
