package com.hhp.concertreservation.B_application.facade.concert;

import com.hhp.concertreservation.B_application.dto.concert.*;
import com.hhp.concertreservation.B_application.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConcertFacade {

    private final ConcertService concertService;

    /**
     * 콘서트 예약 가능한 날짜 조회
     */
    public ConcertAvailableDatesOutput selectConcertAvailableDates(ConcertAvailableDatesInput input) {

        ConcertAvailableDatesOutput output = concertService.selectConcertAvailableDates(input);

        return output;
    }

    /**
     * 콘서트 좌석 전체 조회
     */
    public ConcertAvailableSeatOutput selectAvailableSeats(ConcertAvailableSeatsInput input) {

        ConcertAvailableSeatOutput output = concertService.selectAvailableSeats(input);

        return output;
    }

    /**
     * 좌석 임시 예약
     */
    public void temporarySeatsReservation(TemporarySeatsReservationInput input) {

        concertService.temporarySeatsReservation(input);

    }



}
