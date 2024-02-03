package com.wjx.mq.study.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author Gin
 * @description
 * @date 2024/1/24 23:54
 */
public class CustomProducerCallbackAcks {
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

        //ack
//        properties.put(ProducerConfig.ACKS_CONFIG,"0");
        properties.put(ProducerConfig.ACKS_CONFIG,"1");
//        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,3);

        //1.创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        //2.发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<>("first", "Acks 1 wjx" + i));
        }
        //3.关闭资源
        kafkaProducer.close();
    }
}
