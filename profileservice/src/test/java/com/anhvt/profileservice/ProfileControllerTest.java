/**
 * Copyright 2024
 * Name: ProfileControllerTest
 */
package com.anhvt.profileservice;

import com.google.gson.Gson;
import com.anhvt.commonservice.utils.Constant;
import com.anhvt.profileservice.controller.ProfileController;
import com.anhvt.profileservice.model.ProfileDTO;
import com.anhvt.profileservice.service.ProfileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@ExtendWith(SpringExtension.class)
class ProfileControllerTest {
    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    private ProfileDTO profileDTO;

    Gson gson = new Gson();

    @BeforeEach
    public void setUp(){
        profileDTO = new ProfileDTO(1,
                "test@gmail.com",
                Constant.STATUS_PROFILE_ACTIVE,
                "name",
                200,
                "CUSTOMER");
        ReflectionTestUtils.setField(profileController,"profileService",profileService);
    }

    @Test
    void getAllProfileSuccess(){
        when(profileService.getAllProfile()).thenReturn(Flux.just(profileDTO));
        profileController.getAllProfiles().getBody().
                doOnNext(profileDTO1 -> Assertions.assertEquals(profileDTO,profileDTO1)).subscribe();
    }

    @Test
    void checkDuplicate(){
        when(profileService.checkDuplicate(anyString())).thenReturn(Mono.just(true));
        profileController.checkDuplicate(anyString()).getBody().
                doOnNext(aBoolean -> Assertions.assertEquals(true,aBoolean)).subscribe();
    }

    @Test
    void createNewProfile(){
        when(profileService.createNewProfile(any(ProfileDTO.class))).thenReturn(Mono.just(profileDTO));
        profileController.createProfile(profileDTO).getBody().
                doOnNext(profileDTO1 -> Assertions.assertEquals(profileDTO,profileDTO1)).subscribe();
    }
}

