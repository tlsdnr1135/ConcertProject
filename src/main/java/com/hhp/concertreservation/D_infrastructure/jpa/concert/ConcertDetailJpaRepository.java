package com.hhp.concertreservation.D_infrastructure.jpa.concert;

import com.hhp.concertreservation.C_domain.concert.ConcertDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertDetailJpaRepository extends JpaRepository<ConcertDetail, Long> {


    List<ConcertDetail> findByConcertId(Long concertId);
}
