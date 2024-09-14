/**
 * Copyright 2024
 * Name: EventProducer
 */
package com.anhvt.paymentservice.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

/**
 * Service for producing and sending messages to Kafka topics.
 * Provides a method to send payment request messages and returns a confirmation response.
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@Service
@Slf4j
public class EventProducer {
    @Autowired
    private KafkaSender<String,String> sender;

    public Mono<String> sendPaymentRequest(String topic,String message){
        return sender
                .send(Mono.just(SenderRecord.create(new ProducerRecord<>(topic,message),message)))
                .then().thenReturn("OK");
    }
}
