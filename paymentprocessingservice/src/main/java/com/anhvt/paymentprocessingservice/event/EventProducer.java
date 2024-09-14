/**
 * Copyright 2024
 * Name: EventProducer
 */
package com.anhvt.paymentprocessingservice.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

/**
 * Payment processing producer
 *
 * @author trunganhvu
 * @date 9/13/2024
 */
@Service
@Slf4j
public class EventProducer {
    @Autowired
    private KafkaSender<String, String> sender;

    public Mono<String> sendEvent(String topic, String message){
        log.info("Sending event to topic {}", topic);
        return sender
                .send(Mono.just(SenderRecord.create(new ProducerRecord<>(topic,message),message)))
                .then().thenReturn("OK");
    }
}