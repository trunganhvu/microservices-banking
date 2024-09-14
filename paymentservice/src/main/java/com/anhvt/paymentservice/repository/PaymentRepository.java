package com.anhvt.paymentservice.repository;

import com.anhvt.paymentservice.data.Payment;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Repository interface for CRUD operations on `Payment` entities, extending
 * `ReactiveCrudRepository` for reactive database interactions.
 * Includes a custom query method to find payments by account ID.
 *
 * @author trunganhvu
 * @date 9/12/2024
 */
public interface PaymentRepository extends ReactiveCrudRepository<Payment,Long> {
    @Query("SELECT * FROM payment WHERE account_id = :id")
    Flux<Payment> findByAccountId(String id);
}
