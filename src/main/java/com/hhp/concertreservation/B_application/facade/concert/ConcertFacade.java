package com.hhp.concertreservation.B_application.facade.concert;

import com.hhp.concertreservation.B_application.dto.concert.*;
import com.hhp.concertreservation.B_application.dto.concert.ConcertAvailableDatesOutput.AvailableDateAndSeatInfo;
import com.hhp.concertreservation.B_application.service.ConcertService;
import com.hhp.concertreservation.C_domain.concert.Concert;
import com.hhp.concertreservation.C_domain.concert.ConcertDetail;
import com.hhp.concertreservation.C_domain.concert.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertFacade {

    private final ConcertService concertService;

    /**
     * 콘서트 예약 가능한 날짜 조회
     */
    public ConcertAvailableDatesOutput selectConcertAvailableDates(ConcertAvailableDatesInput input) {

        Concert concert = concertService.findConcertById(input.getConcertId());
        List<ConcertDetail> concertDetailList = concertService.findConcertDetailsByConcertId(concert.getId());
        List<AvailableDateAndSeatInfo> availableDateAndSeatInfos = concertService.convertToAvailableDateAndSeatInfoList(concertDetailList);

        ConcertAvailableDatesOutput output = ConcertAvailableDatesOutput.builder()
                .dateLists(availableDateAndSeatInfos)
                .build();

        return output;
    }

    /**
     * 콘서트 좌석 전체 조회
     */
    public ConcertAvailableSeatOutput selectAvailableSeats(ConcertAvailableSeatsInput input) {

        List<Seat> seatList = concertService.findSeatsByConcertDetailId(input.getConcertDetailId());
        List<ConcertAvailableSeatOutput.SeatInfo> seatInfoList = concertService.convertToSeatInfoList(seatList);
        int totalSeatCnt = concertService.getTotalSeatCnt(seatList);
        int availableSeatCnt = concertService.getAvailableSeatCnt(seatList);

        ConcertAvailableSeatOutput output = ConcertAvailableSeatOutput.builder()
                .totalSeatCnt(totalSeatCnt)
                .availableSeatCnt(availableSeatCnt)
                .seatInfoList(seatInfoList)
                .build();

        return output;
    }

    /**
     * 좌석 임시 예약
     */
    public void temporarySeatsReservation(TemporarySeatsReservationInput input) {

        concertService.temporarySeatsReservation(input);

    }



}
