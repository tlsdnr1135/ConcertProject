package com.hhp.concertreservation.B_application.repository.concert;

import com.hhp.concertreservation.C_domain.concert.ConcertDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertDetailRepository {

    List<ConcertDetail> findConcertDetailByConcertId(Long concertId);

}
