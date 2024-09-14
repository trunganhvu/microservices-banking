/**
 * Copyright 2024
 * Name: PaymentDTO
 */
package com.anhvt.commonservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Payment dto to transfer in services
 *
 * @author trunganhvu
 * @date 9/11/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private long id;
    private String accountId;
    private double amount;
    private String status;
}
