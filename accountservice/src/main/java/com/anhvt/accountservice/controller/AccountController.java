/**
 * Copyright 2024
 * Name: AccountController
 */
package com.anhvt.accountservice.controller;

import com.anhvt.accountservice.model.AccountDTO;
import com.anhvt.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing account operations.
 * Provides an endpoint to check the balance of an account by its ID.
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping(value = "/checkBalance/{id}")
    public Mono<AccountDTO> checkBalance(@PathVariable String id){
        return accountService.checkBalance(id);
    }
}