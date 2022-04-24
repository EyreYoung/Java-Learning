package com.kafka;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

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
        consumer.subscribe(Lists.newArrayList("test"));
        // 也可以传入一个正则表达式 来匹配多个主题
        // 如果出现了新的topic符合正则表达式 立即触发分区再均衡 从而能够读取新topic
//        consumer.subscribe(Pattern.compile("learning.*"));

        manualCommitConsumeSyncAndAsync(consumer);

        /**
         * {@link KafkaConsumer#wakeup()}是唯一可以从其他线程安全调用阻止consumer继续轮询的方法
         */
        consumer.wakeup();

    }

    /**
     * 轮询获取消息
     */
    private static void commonConsume(Consumer<String, String> consumer) {
        try {
            while (true) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));

                log.info("Customer --- Getting Message:");
                for (ConsumerRecord<String, String> record : records) {
                    log.info("offset = {}, partition = {}, key = {}, value = {}",
                            record.offset(), record.partition(), record.key(), record.value());
                }
            }
        } catch (WakeupException ignored) {

        } finally {
            consumer.close();
        }
    }

    /**
     * 手动提交偏移量 同步
     */
    private static void manualCommitConsumeSync(Consumer<String, String> consumer) {

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));

            for (ConsumerRecord<String, String> record : records) {
                log.info("offset = {}, partition = {}, key = {}, value = {}",
                        record.offset(), record.partition(), record.key(), record.value());
            }

            try {
                consumer.commitSync();
            } catch (CommitFailedException e) {
                log.error("消费提交失败 ", e);
            }

        }

    }

    /**
     * 手动提交偏移量 异步
     */
    private static void manualCommitConsumeAsync(Consumer<String, String> consumer) {

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));

            for (ConsumerRecord<String, String> record : records) {
                log.info("offset = {}, partition = {}, key = {}, value = {}",
                        record.offset(), record.partition(), record.key(), record.value());
            }

            consumer.commitAsync((offsets, e) -> {
                if (Objects.nonNull(e)) {
                    log.error("异步提交消费 {} 失败", offsets, e);
                }
            });

        }

    }

    /**
     * 手动提交偏移量 如果异步提交出错 再同步提交
     */
    private static void manualCommitConsumeSyncAndAsync(Consumer<String, String> consumer) {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));

                for (ConsumerRecord<String, String> record : records) {
                    log.info("offset = {}, partition = {}, key = {}, value = {}",
                            record.offset(), record.partition(), record.key(), record.value());
                }

                consumer.commitAsync();
            }
        } catch (Exception e) {
            log.error("异步提交消费失败", e);
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    /**
     * 手动提交特定偏移量
     * 否则{@link KafkaConsumer#commitSync()}提交的频率就是{@link KafkaConsumer#poll(Duration)}的频率
     */
    private static void manualCommitCertainOffsetConsume(Consumer<String, String> consumer) {

        int count = 0;

        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> record : records) {
                log.info("offset = {}, partition = {}, key = {}, value = {}",
                        record.offset(), record.partition(), record.key(), record.value());

                currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1, "no metaData"));

                if (count % 1000 == 0) {
                    // 提交时传入自定义的Offset Map
                    consumer.commitAsync(currentOffsets, null);
                }

                count++;

            }
        }
    }

}
