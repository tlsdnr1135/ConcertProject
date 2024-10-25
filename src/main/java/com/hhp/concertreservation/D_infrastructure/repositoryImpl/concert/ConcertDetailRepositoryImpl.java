package com.hhp.concertreservation.D_infrastructure.repositoryImpl.concert;

import com.hhp.concertreservation.B_application.repository.concert.ConcertDetailRepository;
import com.hhp.concertreservation.C_domain.concert.ConcertDetail;
import com.hhp.concertreservation.D_infrastructure.jpa.concert.ConcertDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ConcertDetailRepositoryImpl implements ConcertDetailRepository {

    private final ConcertDetailJpaRepository concertDetailJpaRepository;


    @Override
    public List<ConcertDetail> findConcertDetailByConcertId(Long concertId) {
        List<ConcertDetail> list = concertDetailJpaRepository.findByConcertId(concertId);
        return list;
    }
}
