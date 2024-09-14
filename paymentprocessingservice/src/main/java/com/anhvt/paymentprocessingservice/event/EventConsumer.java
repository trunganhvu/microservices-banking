/**
 * Copyright 2024
 * Name: EventConsumer
 */
package com.anhvt.paymentprocessingservice.event;

import com.anhvt.commonservice.model.PaymentDTO;
import com.anhvt.commonservice.utils.Constant;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.security.SecureRandom;
import java.util.Collections;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@Service
@Slf4j
public class EventConsumer {
    Gson gson = new Gson();
    SecureRandom random = new SecureRandom();

    @Autowired
    EventProducer eventProducer;

    public EventConsumer(ReceiverOptions<String, String> receiverOptions){
        log.info("Initializing Receiver Payment Processing");
        KafkaReceiver
                .create(receiverOptions.subscription(Collections.singleton(Constant.PAYMENT_CREATED_TOPIC)))
                .receive().subscribe(this::paymentCreated);
    }

    @SneakyThrows
    public void paymentCreated(ReceiverRecord<String,String> receiverRecord){
        log.info("Processing payment: Start");
        PaymentDTO paymentDTO = gson.fromJson(receiverRecord.value(), PaymentDTO.class);
        String[] randomStatus = {
                Constant.STATUS_PAYMENT_REJECTED,
                Constant.STATUS_PAYMENT_SUCCESSFUL
        };
        // Set random status transaction: successful, rejected
        int index = random.nextInt(randomStatus.length);
        paymentDTO.setStatus(randomStatus[index]);
        Thread.sleep(1000);
        log.info("Processing payment: Producer completed");
        eventProducer
                .sendEvent(Constant.PAYMENT_COMPLETED_TOPIC, gson.toJson(paymentDTO))
                .subscribe();
        log.info("Processing payment: End");
    }
}
