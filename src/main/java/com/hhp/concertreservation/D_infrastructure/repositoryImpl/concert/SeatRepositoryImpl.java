package com.hhp.concertreservation.D_infrastructure.repositoryImpl.concert;

import com.hhp.concertreservation.B_application.repository.concert.SeatRepository;
import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import com.hhp.concertreservation.D_infrastructure.jpa.concert.SeatJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SeatRepositoryImpl implements SeatRepository {

    private final SeatJpaRepository seatJpaRepository;

    @Override
    public List<Seat> findSeatsByConcertDetailId(Long concertDetailId) {
        return seatJpaRepository.findSeatsByConcertDetailId(concertDetailId);
    }

    @Override
    public Optional<Seat> findSeatsBySeatId(Long seatId) {
        return seatJpaRepository.findById(seatId);
    }

    @Override
    public Optional<Seat> findSeatsBySeatIdAndStatus(Long seatId, SeatStatus status) {
        Optional<Seat> seatOp = seatJpaRepository.findSeatsByIdAndStatus(seatId, status);
        return seatOp;
    }

}
