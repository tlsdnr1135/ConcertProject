package com.hhp.concertreservation.B_application.repository.concert;

import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository {

    Optional<Seat> findSeatsBySeatId(Long seatId);
    Optional<Seat> findSeatsBySeatIdAndStatus(Long seatId, SeatStatus status);
    List<Seat> findSeatsByConcertDetailId(Long concertDetailId);

}
