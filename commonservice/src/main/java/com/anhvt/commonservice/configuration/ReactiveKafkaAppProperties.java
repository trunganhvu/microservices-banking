/**
 * Copyright 2024
 * Name: ReactiveKafkaAppProperties
 */
package com.anhvt.commonservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Properties of kafka
 *
 * @author trunganhvu
 * @date 9/7/2024
 */
@Configuration
public class ReactiveKafkaAppProperties {
    @Value("${kafka.bootstrap.servers}")
    String bootstrapServers;

    @Value("${payment.kafka.consumer-group-id}")
    String consumerGroupId;
}
