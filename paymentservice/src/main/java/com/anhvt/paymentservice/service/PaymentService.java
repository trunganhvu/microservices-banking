/**
 * Copyright 2024
 * Name: PaymentService
 */
package com.anhvt.paymentservice.service;

import com.google.gson.Gson;
import com.anhvt.commonservice.common.CommonException;
import com.anhvt.commonservice.model.AccountDTO;
import com.anhvt.commonservice.utils.Constant;
import com.anhvt.paymentservice.event.EventProducer;
import com.anhvt.paymentservice.model.PaymentDTO;
import com.anhvt.paymentservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for managing payment operations.
 * Handles retrieving all payments for an account, making new payments,
 * creating new payment records, and updating payment statuses.
 * Integrates with external services via `WebClient` and produces events via `EventProducer`.
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@Service
@Slf4j
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    WebClient webClientAccount;

    @Autowired
    EventProducer eventProducer;

    Gson gson = new Gson();
    public Flux<PaymentDTO> getAllPayment(String id){
        return paymentRepository.findByAccountId(id)
                .map(PaymentDTO::entityToDto)
                .switchIfEmpty(Mono.error(new CommonException(
                        "P02",
                        "Account don't have payment",
                        HttpStatus.NOT_FOUND)));
    }

    public Mono<PaymentDTO> makePayment(PaymentDTO paymentDTO){
        return webClientAccount.get()
                .uri("/checkBalance/" + paymentDTO.getAccountId())
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .flatMap(accountDTO -> {
                    if (paymentDTO.getAmount() <= accountDTO.getBalance()){
                        paymentDTO.setStatus(Constant.STATUS_PAYMENT_CREATING);
                    } else {
                        log.info("Balance not enough");
                        throw new CommonException("P01",
                                "Balance not enough",
                                HttpStatus.BAD_REQUEST);
                    }
                    return createNewPayment(paymentDTO);
                });
    }

    public Mono<PaymentDTO> createNewPayment(PaymentDTO paymentDTO){
        return Mono.just(paymentDTO)
                .map(PaymentDTO::dtoToEntity)
                .flatMap(payment -> paymentRepository.save(payment))
                .map(PaymentDTO::entityToDto)
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .doOnSuccess(paymentDTO1 ->
                        eventProducer.sendPaymentRequest(
                                Constant.PAYMENT_REQUEST_TOPIC, gson.toJson(paymentDTO1)
                        ).subscribe()
                );
    }

    public Mono<PaymentDTO> updateStatusPayment(PaymentDTO paymentDTO){
        return paymentRepository.findById(paymentDTO.getId())
                .switchIfEmpty(Mono.error(new CommonException("P03",
                        "Payment not found",
                        HttpStatus.NOT_FOUND)))
                .flatMap(payment -> {
                    log.info("Updating status payment");
                    payment.setStatus(paymentDTO.getStatus());
                    return paymentRepository.save(payment);
                })
                .map(PaymentDTO::entityToDto);
    }
}

