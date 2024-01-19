package com.wjx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gin
 * @description
 * @date 2024/1/10 14:54
 */
@RestController
@RequestMapping("test")
public class TestController {
    private static final AtomicInteger counter = new AtomicInteger(0);


    /**
     * 私有线程池
     * Thread 为 GC ROOTS
     * 可达性 核心线程不会被销毁 则线程池也不会销毁
     */
    @RequestMapping("thread")
    public void threadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("before" + counter.get());
        //（1）可以正常输出不影响业务
        // 核心线程超过keepAlive时间也会被回收 私有的时候可以用这个
        // 但是作为bean最好是不设置 保持false 这样可以减少核心线程创建开销
//        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.submit(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("doing something : " + counter.incrementAndGet());
            } catch (InterruptedException e) {
                System.out.println("error");
            } finally {
                System.out.println("finally : " + counter.get());
            }
        });
        //（2）可以正常输出不影响业务
//        threadPoolExecutor.shutdown();
        //（3）直接终止业务
//        threadPoolExecutor.shutdownNow();
        System.out.println("after" + counter.get());
//        总结 推荐1和2
    }

}
