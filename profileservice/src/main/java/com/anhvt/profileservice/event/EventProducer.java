/**
 * Copyright 2024
 * Name: EventProducer
 */
package com.anhvt.profileservice.event;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

/**
 * EventProducer is responsible for sending events to a specified Kafka topic.
 *
 * @author trunganhvu
 * @date 9/7/2024
 */
@Service
public class EventProducer {
    @Autowired
    private KafkaSender<String, String> sender;

//    public Mono<String> sendEvent(String topic, String message) {
//        return sender
//                .send(Mono.just(SenderRecord.create(new ProducerRecord<>(topic, message), message)))
//                .doOnError(e -> {
//                    // Log the error for better debugging
//                    System.err.println("My error occurred while sending event: " + e.getMessage());
//                })
//                .then()
//                .thenReturn("OK");
//    }

    public Mono<String> send(String topic, String message){
        return sender
                .send(Mono.just(SenderRecord.create(new ProducerRecord<>(topic,message),message)))
                .doOnError(e -> {
                    // Log the error for better debugging
                    System.err.println("My error occurred while sending event: " + e.getMessage());
                })
                .then()
                .thenReturn("OK");
    }
}
