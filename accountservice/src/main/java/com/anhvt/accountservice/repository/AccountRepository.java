/**
 * Copyright 2024
 * Name: AccountRepository
 */
package com.anhvt.accountservice.repository;

import com.anhvt.accountservice.data.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Repository interface for CRUD operations on `Account` entities, extending
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
public interface AccountRepository extends ReactiveCrudRepository<Account, String> {
}
