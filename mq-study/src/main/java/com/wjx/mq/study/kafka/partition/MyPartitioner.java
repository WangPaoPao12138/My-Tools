package com.wjx.mq.study.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义分区器
 *
 * @author Gin
 * @description
 * @date 2024/1/24 23:34
 */
public class MyPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获取数据
        String msgValue = value.toString();
        int targetPartition;
        if (msgValue.contains("wjx")) {
            targetPartition = 0;
        } else {
            targetPartition = 1;
        }
        return targetPartition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
