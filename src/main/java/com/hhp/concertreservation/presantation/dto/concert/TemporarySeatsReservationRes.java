package com.hhp.concertreservation.presantation.dto.concert;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TemporarySeatsReservationRes {

    private List<Integer> reservationSeatList;

    @Builder
    public TemporarySeatsReservationRes(List<Integer> reservationSeatList) {
        this.reservationSeatList = reservationSeatList;
    }
}
