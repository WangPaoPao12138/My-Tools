package com.wjx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gin
 * @description
 * @date 2023/12/15 21:31
 */
@SpringBootApplication
@MapperScan("com.wjx")
public class FullTextSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FullTextSearchApplication.class);
    }
}
