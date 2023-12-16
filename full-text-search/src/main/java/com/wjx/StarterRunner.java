package com.wjx;

import com.wjx.controller.util.LuceneUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class StarterRunner implements ApplicationRunner {
    @Resource
    private LuceneUtil luceneUtil;

    @Override
    public void run(ApplicationArguments args) {
        try {
            luceneUtil.createIndex();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("123456");
        }
    }
}
