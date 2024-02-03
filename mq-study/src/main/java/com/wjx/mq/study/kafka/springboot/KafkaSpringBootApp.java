package com.wjx.mq.study.kafka.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Gin
 * @description
 * @date 2024/2/3 16:04
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KafkaSpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringBootApp.class);
    }
}
