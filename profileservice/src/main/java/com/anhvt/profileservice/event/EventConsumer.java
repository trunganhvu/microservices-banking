/**
 * Copyright 2024
 * Name: EventConsumer
 */
package com.anhvt.profileservice.event;

import com.anhvt.commonservice.utils.Constant;
import com.anhvt.profileservice.model.ProfileDTO;
import com.anhvt.profileservice.service.ProfileService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;

/**
 * EventConsumer listens to Kafka topics and processes "Profile Onboarded" events.
 *
 * @author trunganhvu
 * @date 9/11/2024
 */
@Service
@Slf4j
public class EventConsumer {
    Gson gson = new Gson();

    @Autowired
    ProfileService profileService;

    public EventConsumer(ReceiverOptions<String, String> receiverOptions){
        KafkaReceiver
                .create(receiverOptions.subscription(Collections.singleton(Constant.PROFILE_ONBOARDED_TOPIC)))
                .receive()
                .subscribe(this::profileOnboarded);
    }
    public void profileOnboarded(ReceiverRecord<String,String> receiverRecord){
        log.info("Profile Onboarded event");
        ProfileDTO dto = gson.fromJson(receiverRecord.value(),ProfileDTO.class);
        profileService.updateStatusProfile(dto).subscribe();
    }
}
