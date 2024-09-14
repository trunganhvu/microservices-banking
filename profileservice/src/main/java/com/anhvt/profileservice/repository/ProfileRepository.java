package com.anhvt.profileservice.repository;

import com.anhvt.profileservice.data.Profile;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * Repository to access profile table
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public interface ProfileRepository extends ReactiveCrudRepository<Profile, Long> {
    public Mono<Profile> findByEmail(String email);
}
