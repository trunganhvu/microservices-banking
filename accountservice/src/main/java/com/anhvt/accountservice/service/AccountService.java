/**
 * Copyright 2024
 * Name: AccountService
 */
package com.anhvt.accountservice.service;

import com.anhvt.commonservice.common.CommonException;
import com.anhvt.accountservice.model.AccountDTO;
import com.anhvt.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * Service class for managing account operations including creation, balance checking,
 * booking amounts, and handling reservations with error logging and exception handling.
 *
 * @author trunganhvu
 * @date 9/7/2024
 */
@Service
@Slf4j
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Mono<AccountDTO> createNewAccount(AccountDTO accountDTO){
        return Mono.just(accountDTO)
                .map(AccountDTO::dtoToEntity)
                .flatMap(account -> {
                    account.setId(String.valueOf(UUID.randomUUID()));
                    return accountRepository.save(account);
                })
                .map(AccountDTO::entityToModel)
                .doOnError(throwable -> log.error(throwable.getMessage()));
    }

    public Mono<AccountDTO> checkBalance(String id){
        return findById(id);
    }

    public Mono<AccountDTO> findById(String id){
        return accountRepository.findById(id)
                .map(AccountDTO::entityToModel)
                .switchIfEmpty(Mono.error(new CommonException("A01", "Account not found",
                        HttpStatus.NOT_FOUND)));
    }

    public Mono<Boolean> bookAmount(double amount, String accountId){
        return accountRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new CommonException("A01", "Account not found",
                        HttpStatus.NOT_FOUND)))
                .flatMap(account -> {
                    if(account.getBalance() < amount + account.getReserved()){
                        return Mono.just(false);
                    }
                    account.setReserved(account.getReserved() + amount);
                    return accountRepository.save(account);
                })
                .flatMap(account -> Mono.just(true));
    }

    public Mono<AccountDTO> subtract(double amount, String accountId){
        return accountRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new CommonException("A01", "Account not found",
                        HttpStatus.NOT_FOUND)))
                .flatMap(account -> {
                    account.setReserved(account.getReserved() - amount);
                    account.setBalance(account.getBalance() - amount);
                    return accountRepository.save(account);
                }).map(AccountDTO::entityToModel);
    }

    public Mono<AccountDTO> rollbackReserved(double amount, String accountId){
        return accountRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new CommonException("A01", "Account not found",
                        HttpStatus.NOT_FOUND)))
                .flatMap(account -> {
                    account.setReserved(account.getReserved() - amount);
                    return accountRepository.save(account);
                }).map(AccountDTO::entityToModel);
    }
}