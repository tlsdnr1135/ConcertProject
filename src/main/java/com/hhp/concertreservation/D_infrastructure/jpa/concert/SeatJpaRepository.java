package com.hhp.concertreservation.D_infrastructure.jpa.concert;

import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatJpaRepository extends JpaRepository<Seat, Long> {

    List<Seat> findSeatsByConcertDetailId(Long concertDetailId);
    Optional<Seat> findSeatsByIdAndStatus(Long seatId, SeatStatus status);

}
