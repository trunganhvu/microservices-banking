/**
 * Copyright 2024
 * Name: Payment
 */
package com.anhvt.paymentservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Payment entity object
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@Data
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private long id;

    @Column("account_id")
    private String accountId;
    private double amount;
    private String status;
}
