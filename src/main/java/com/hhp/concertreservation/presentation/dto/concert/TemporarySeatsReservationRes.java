package com.hhp.concertreservation.presentation.dto.concert;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TemporarySeatsReservationRes {

    private List<Integer> reservationSeatList;

    @Builder
    public TemporarySeatsReservationRes(List<Integer> reservationSeatList) {
        this.reservationSeatList = reservationSeatList;
    }
}
