/**
 * Copyright 2024
 * Name: ProfileDTO
 */
package com.anhvt.profileservice.model;

import com.anhvt.profileservice.data.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Profile dto to transfer
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private long id;
    private String email;
    private String status;
    private String name;
    private double initialBalance;
    private String role;

    public static Profile dtoToEntity(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setEmail(profileDTO.getEmail());
        profile.setStatus(profileDTO.getStatus());
        profile.setName(profileDTO.getName());
        profile.setRole(profileDTO.getRole());
        return profile;
    }
    public static ProfileDTO entityToDto(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setStatus(profile.getStatus());
        profileDTO.setName(profile.getName());
        profileDTO.setRole(profile.getRole());
        return profileDTO;
    }
}
