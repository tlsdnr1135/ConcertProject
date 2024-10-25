package com.hhp.concertreservation.D_infrastructure.jpa.queue;

import com.hhp.concertreservation.C_domain.queue.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {
    boolean existsByUserId(Long userId);
    Optional<Token> findByUserId(Long userId);
    Optional<Token> findByToken(String token);
}
