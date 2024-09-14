/**
 * Copyright 2024
 * Name: PaymentDTO
 */
package com.anhvt.paymentservice.model;

import com.anhvt.paymentservice.data.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Payment transfer object
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private long id;
    private String accountId;
    private double amount;
    private String status;

    public static PaymentDTO entityToDto(Payment payment){
        return  PaymentDTO.builder()
                .id(payment.getId())
                .accountId(payment.getAccountId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();
    }
    public static Payment dtoToEntity(PaymentDTO paymentDTO){
        return  Payment.builder()
                .accountId(paymentDTO.getAccountId())
                .amount(paymentDTO.getAmount())
                .status(paymentDTO.getStatus())
                .build();
    }
}