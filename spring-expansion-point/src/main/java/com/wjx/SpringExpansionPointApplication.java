package com.wjx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Gin
 * @description
 * @date 2024/1/10 14:49
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringExpansionPointApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringExpansionPointApplication.class);
    }
}