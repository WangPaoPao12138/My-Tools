package com.wjx.mq.study.kafka.producer;

import com.wjx.mq.study.kafka.partition.MyPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 分区callback
 *
 * @author Gin
 * @description
 * @date 2024/1/24 23:22
 */
public class CustomProducerCallbackPartitions {
    public static void main(String[] args) {
        //0属性配置
        Properties properties = new Properties();
        //连接集群 bootstrap.servers
        /* 将windows下的hosts 主机ip和虚拟机名映射做了 */
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "linux0:9092,linux1:9092,linux2:9092");
        //指定对应key 和 value的序列化
        //properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //***在此关联自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());

        //1.创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        //2.发送数据
        for (int i = 0; i < 5; i++) {
            //指定 partitions 分区
//            kafkaProducer.send(new ProducerRecord<>("first", 1,"","Callback wjx" + i), new Callback() {
            //通过key hash 取模
            kafkaProducer.send(new ProducerRecord<>("first", 0,"a","Callback wjx" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println("主题：" + metadata.topic() + "     分区：" + metadata.partition());
                    }
                }
            });
        }
        //3.关闭资源
        kafkaProducer.close();
    }
}
