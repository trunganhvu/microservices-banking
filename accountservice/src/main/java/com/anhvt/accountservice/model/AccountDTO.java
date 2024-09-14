/**
 * Copyright 2024
 * Name: AccountDTO
 */
package com.anhvt.accountservice.model;

import com.anhvt.accountservice.data.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account transfer object
 *
 * @author trunganhvu
 * @date 9/7/2024
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

    public static AccountDTO entityToModel(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .reserved(account.getReserved())
                .build();
    }

    public static Account dtoToEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setEmail(accountDTO.getEmail());
        account.setCurrency(accountDTO.getCurrency());
        account.setBalance(accountDTO.getBalance());
        account.setReserved(accountDTO.getReserved());
        return account;
    }
}
