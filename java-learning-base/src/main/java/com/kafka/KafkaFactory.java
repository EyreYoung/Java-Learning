package com.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

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
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        producer = new KafkaProducer<>(properties);
        consumer = new KafkaConsumer<>(properties);
    }
}
