package org.example.javaspring.performanceTests;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaPerformanceTest {

    @Test
    public void sendMessagesToKafka() {
        String topic = "test-topic";
        int numMessages = 100;
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            long start = System.nanoTime();

            for (int i = 0; i < numMessages; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key-" + i, "message-" + i);
                producer.send(record);
            }

            producer.flush();

            long end = System.nanoTime();
            long duration = TimeUnit.NANOSECONDS.toMillis(end - start);

            System.out.printf("Sent %d messages in %d ms (avg: %.2f ms/msg)%n",
                    numMessages, duration, (double) duration / numMessages);
        }
    }
}
