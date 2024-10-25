package com.hhp.concertreservation.D_infrastructure.jpa.concert;

import com.hhp.concertreservation.C_domain.concert.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerJpaRepository extends JpaRepository<Singer, Long> {
}
