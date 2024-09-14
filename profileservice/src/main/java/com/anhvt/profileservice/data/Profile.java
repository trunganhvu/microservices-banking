/**
 * Copyright 2024
 * Name: Profile
 */
package com.anhvt.profileservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Profile data entity object
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    private long id;
    private String email;
    private String name;
    private String status;
    private String role;
}
