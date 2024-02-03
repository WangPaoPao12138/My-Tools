package com.wjx.mq.study.kafka.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


/**
 * @author Gin
 * @description
 * @date 2024/2/3 17:20
 */
@Configuration
public class KafkaConsumer {
    // 指定要监听的 topic
    @KafkaListener(topics = "first")
    public void consumeTopic(String msg) { // 参数: 收到的 value
        System.out.println("收到的信息: " + msg);
    }
}
