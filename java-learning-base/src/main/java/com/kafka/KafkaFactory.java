package com.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.internals.Sender;
import org.apache.kafka.clients.producer.internals.TransactionManager;

import java.util.Properties;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/22
 */

@Slf4j
public class KafkaFactory<K, V> {

    private static final String HOST = "localhost:9092";

    private final Producer<K, V> producer;

    private final Consumer<K, V> consumer;

    public Producer<K, V> getProducer() {
        return producer;
    }

    public Consumer<K, V> getConsumer() {
        return consumer;
    }

    public KafkaFactory() {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, 3);
        producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        producerProperties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, 300000); // 定期去更新MetaData的时间间隔
        producerProperties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "zstd"); // 压缩算法
        // 可以保证单Partition消息幂等，单会话的消息幂等
        producerProperties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // 开启幂等性
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer"); // key的序列化器
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer"); // value的序列化器

        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "cg1");
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        consumerProperties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100); // 每次poll拉取数据的数量
        consumerProperties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5000); // 期望的两次poll间隔时间
        consumerProperties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 2000);

        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        /**
         * 创建KafkaProducer后会开启一个Sender线程{@link Sender#run()}
         * 如果开启了幂等性{@link ProducerConfig#ENABLE_IDEMPOTENCE_CONFIG}
         * 则会给发出的每个消息附上producerId和sequenceId{@link TransactionManager#bumpIdempotentEpochAndResetIdIfNeeded()}
         * 避免发送消息没收到ack 重试后在broker写入两条
         */
        producer = new KafkaProducer<>(producerProperties);
        consumer = new KafkaConsumer<>(consumerProperties);
    }
}
