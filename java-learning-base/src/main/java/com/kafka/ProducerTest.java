package com.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/2/23
 */

public class ProducerTest {

    public static void main(String[] args) {

        Producer<String, String> producer = new KafkaFactory<String, String>().getProducer();
        try {
            for (int i = 0; i < 100000; i++) {
                String msg = "Message " + i;
                producer.send(new ProducerRecord<>("test", msg));
                System.out.println("Sent: " + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
