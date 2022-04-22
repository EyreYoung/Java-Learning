package com.kafka;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/22
 */

@Slf4j
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<String, String> consumer = new KafkaFactory<String, String>().getConsumer();
        consumer.subscribe(Lists.newArrayList("learning"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                log.info("Customer --- Getting Message:");
                for (ConsumerRecord<String, String> record : records) {
                    log.info("offset = {}, partition = {}, key = {}, value = {}",
                            record.offset(), record.partition(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }


    }
}
