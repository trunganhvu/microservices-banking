/**
 * Copyright 2024
 * Name: AppConfig
 */
package com.anhvt.paymentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for setting up application-wide beans.
 * Configures a `WebClient` with a base URL for account-related operations.
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@Configuration
public class AppConfig {

    @Value("${accountBaseUrl}")
    private String accountBaseUrl;

    @Bean
    WebClient webClientAccount(){
        return WebClient.builder()
                .baseUrl(accountBaseUrl)
                .build();
    }
}
