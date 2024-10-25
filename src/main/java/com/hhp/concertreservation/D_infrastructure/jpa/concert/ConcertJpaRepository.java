package com.hhp.concertreservation.D_infrastructure.jpa.concert;

import com.hhp.concertreservation.C_domain.concert.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {
    Optional<Concert> findById(Long concertId);
}
