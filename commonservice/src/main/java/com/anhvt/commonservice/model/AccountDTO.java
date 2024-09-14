/**
 * Copyright 2024
 * Name: AccountDTO
 */
package com.anhvt.commonservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account dto to transfer in services
 *
 * @author trunganhvu
 * @date 9/11/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private String id;
    private String email;
    private String currency;
    private double balance;
    private double reserved;
}
