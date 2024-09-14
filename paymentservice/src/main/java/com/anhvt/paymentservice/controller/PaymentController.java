/**
 * Copyright 2024
 * Name: PaymentController
 */
package com.anhvt.paymentservice.controller;

import com.anhvt.paymentservice.model.PaymentDTO;
import com.anhvt.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for handling payment operations.
 * Provides endpoints to retrieve all payments for an account and to initiate a new payment.
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Flux<PaymentDTO>> getAllPayment(@PathVariable String id){
        return ResponseEntity.ok(paymentService.getAllPayment(id));
    }

    @PostMapping(value = "/payment")
    public ResponseEntity<Mono<PaymentDTO>> makePayment(@RequestBody PaymentDTO paymentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.makePayment(paymentDTO));
    }
}