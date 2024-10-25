package com.hhp.concertreservation.D_infrastructure.repositoryImpl.concert;

import com.hhp.concertreservation.B_application.repository.concert.ConcertRepository;
import com.hhp.concertreservation.C_domain.concert.Concert;
import com.hhp.concertreservation.C_domain.concert.ConcertDetail;
import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import com.hhp.concertreservation.D_infrastructure.jpa.concert.ConcertDetailJpaRepository;
import com.hhp.concertreservation.D_infrastructure.jpa.concert.ConcertJpaRepository;
import com.hhp.concertreservation.D_infrastructure.jpa.concert.SeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;

    @Override
    public Optional<Concert> findConcertByConcertId(Long concertId) {
        Optional<Concert> concert = concertJpaRepository.findById(concertId);
        return concert;
    }




}