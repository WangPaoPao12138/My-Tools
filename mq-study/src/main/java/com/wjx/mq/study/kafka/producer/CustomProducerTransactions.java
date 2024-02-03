package com.wjx.mq.study.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * kafka事务
 *
 * @author Gin
 * @description
 * @date 2024/1/25 0:04
 */
public class CustomProducerTransactions {
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
        //指定事务id
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional_id_wjx_01");
        //1.创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        //初始化事务
        kafkaProducer.initTransactions();
        //开启事务
        kafkaProducer.beginTransaction();
        try {
            //2.成功发送数据
            for (int i = 0; i < 5; i++) {
                kafkaProducer.send(new ProducerRecord<>("first", "transactional wjx" + i));
            }
            //事务异常
//            int i = 1/0;
            //提交事务
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            //终止事务
            kafkaProducer.abortTransaction();
        } finally {
            //3.关闭资源
            kafkaProducer.close();
        }
    }
}
