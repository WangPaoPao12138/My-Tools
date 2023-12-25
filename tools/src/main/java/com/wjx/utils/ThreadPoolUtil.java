package com.wjx.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @author Gin
 * @description
 * @date 2023/12/23 14:38
 */
//交给Spring管理
//@Component
public class ThreadPoolUtil {

    //可以自由配置
//    @Value("${gin.threadPool.corePoolSize:10}")
    private int corePoolSize = 10;
    //可以自由配置
//    @Value("${gin.threadPool.maximumPoolSize:10}")
    private int maximumPoolSize = 10;

    private static ThreadPoolExecutor executor;

    //Spring容器启动初始化线程池配置
    @PostConstruct
    public void initProcessorThreadPool() {
        executor = new ThreadPoolExecutor(
                corePoolSize
                , maximumPoolSize
                , 60
                , TimeUnit.SECONDS
                , new SynchronousQueue<>(true) //公平策略,FIFO
                , new ThreadPoolExecutor.CallerRunsPolicy()//默认主线程阻塞执行多线程方法
        );
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

    public static void submit(Runnable runnable) {
        executor.submit(runnable);
    }

    //可以自定义策略
    private static class BlockRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException ignored) {
            }
        }
    }

    //CountDownLatch
    //CountDownLatch count = new CountDownLatch(子线程数量);
    //使用多线程时,由于子线程都是异步执行的,所以要等所有子线程结束的话,
    //可以用CountDownLatch阻塞主线程,子线程结束时通过countDown()方法减少计数值,
    //切记countDown()要放在finally中,以防子线程异常中断导致的主线程一直阻塞;

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        ThreadPoolUtil threadPoolUtil = new ThreadPoolUtil();
        threadPoolUtil.initProcessorThreadPool();
        HashMap<String, String> map = new HashMap<>();
        ThreadPoolUtil.submit(() -> {
            map.put("1", "1");
            latch.countDown();
        });
        ThreadPoolUtil.submit(() -> {
            try {
                map.put("2", "2");
                map.put("3", "3");
            } catch (Exception e) {

            } finally {
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}