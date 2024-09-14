/**
 * Copyright 2024
 * Name: ProfileDTO
 */
package com.anhvt.commonservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Profile dto to transfer in services
 *
 * @author trunganhvu
 * @date 9/8/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
    private long id;
    private String email;
    private String status;
    private double initialBalance;
    private String name;
    private String role;
}
