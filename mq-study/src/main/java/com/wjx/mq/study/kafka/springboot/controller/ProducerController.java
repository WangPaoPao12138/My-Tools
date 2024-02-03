package com.wjx.mq.study.kafka.springboot.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Gin
 * @description
 * @date 2024/2/3 16:04
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Resource
    KafkaTemplate<String,String> kafka;

    @GetMapping
    public String data(String msg) {
        //通过kafka发送数据
        kafka.send("first",msg);
        return "ok";
    }
}
