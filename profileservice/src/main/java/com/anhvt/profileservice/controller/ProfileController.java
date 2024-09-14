/**
 * Copyright 2024
 * Name: ProfileController
 */
package com.anhvt.profileservice.controller;

import com.anhvt.commonservice.utils.CommonFunction;
import com.anhvt.commonservice.utils.Constant;
import com.anhvt.profileservice.model.ProfileDTO;
import com.anhvt.profileservice.service.ProfileService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;

/**
 * ProfileController handles HTTP requests related to user profiles, providing APIs for
 * fetching, checking email duplication, and creating new profiles.
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    Gson gson = new Gson();

    @GetMapping
    public ResponseEntity<Flux<ProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfile());
    }

    @GetMapping("/checkDuplicate/{email}")
    public ResponseEntity<Mono<Boolean>> checkDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(profileService.checkDuplicate(email));
    }

    @PostMapping
    public ResponseEntity<Mono<ProfileDTO>> createProfile(@RequestBody ProfileDTO profileDTO) {
        // Load input stream to validate json
        InputStream inputStream = ProfileController.class
                .getClassLoader()
                .getResourceAsStream(Constant.JSON_REQ_CREATE_PROFILE);
        // Validate input json
        CommonFunction.jsonValidate(inputStream, gson.toJson(profileDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profileService.createProfile(profileDTO));
    }
}
