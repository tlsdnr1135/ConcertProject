package com.hhp.concertreservation.D_infrastructure.jpa;

import com.hhp.concertreservation.C_domain.queue.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {
}
