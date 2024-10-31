package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.concert.*;
import com.hhp.concertreservation.B_application.dto.concert.ConcertAvailableDatesOutput.AvailableDateAndSeat;
import com.hhp.concertreservation.B_application.dto.concert.ConcertAvailableSeatOutput.SeatInfo;
import com.hhp.concertreservation.B_application.repository.concert.ConcertDetailRepository;
import com.hhp.concertreservation.B_application.repository.concert.ConcertRepository;
import com.hhp.concertreservation.B_application.repository.concert.SeatRepository;
import com.hhp.concertreservation.C_domain.concert.Concert;
import com.hhp.concertreservation.C_domain.concert.ConcertDetail;
import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import com.hhp.concertreservation.F_common.SystemClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertDetailRepository concertDetailRepository;
    private final SeatRepository seatRepository;

    private final SystemClockHolder SystemClockHolder;

    /**
     * 예약 가능한 날짜 조회 
     */
    public ConcertAvailableDatesOutput selectConcertAvailableDates(ConcertAvailableDatesInput input) {

        // 콘서트 존재 확인.
        Concert concert = concertRepository.findConcertByConcertId(input.getConcertId()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당 콘서트가 존재하지 않습니다.")
        );

        //콘서트 날짜 조회 list
        List<ConcertDetail> concertDetailList = concertDetailRepository.findConcertDetailByConcertId(concert.getId());

        List<AvailableDateAndSeat> dateLists = concertDetailList.stream().map(concertDetail -> {
            //TODO 날짜별 예약 가능한 좌석 수, concertRepository.findSeatCountByConcertDetailIdAndTemporary
            return AvailableDateAndSeat.builder()
                    .id(concertDetail.getId())
                    .date(concertDetail.getPerformanceDate())
                    .maxSeatCnt(concertDetail.getMaxSeatCount())
                    .build();
        }).toList();

        ConcertAvailableDatesOutput output = ConcertAvailableDatesOutput.builder()
                .dateLists(dateLists)
                .build();
        return output;
    }

    /**
     * 좌석 조회
     */
    public ConcertAvailableSeatOutput selectAvailableSeats(ConcertAvailableSeatsInput input) {
        List<Seat> seatList = seatRepository.findSeatsByConcertDetailId(input.getConcertDetailId());
        int totalSeatCnt = 0;
        int availableSeatCnt = 0;

        List<SeatInfo> seatInfoList = seatList.stream().map(seat -> {
            SeatInfo seatInfo = SeatInfo.builder()
                    .seatId(seat.getId())
                    .seatNumber(seat.getSeatNumber())
                    .status(seat.getStatus().toString())
                    .build();
            return seatInfo;
        }).toList();

        totalSeatCnt = seatInfoList.size();
        availableSeatCnt = (int) seatInfoList.stream()
                .filter(seatInfo -> SeatStatus.EMPTY.toString().equals(seatInfo.getStatus()))
                .count();

        ConcertAvailableSeatOutput result = ConcertAvailableSeatOutput.builder()
                .totalSeatCnt(totalSeatCnt)
                .availableSeatCnt(availableSeatCnt)
                .seatInfoList(seatInfoList)
                .build();
        return result;
    }


    /**
     * 좌석 임시 예약
     */
    public void temporarySeatsReservation(TemporarySeatsReservationInput input) {
        // 좌석 아이디 가져옴.
        Seat seat = seatRepository.findSeatsBySeatIdAndStatus(input.getSeatId(), SeatStatus.EMPTY).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당 좌석 이미 예약되어있습니다.")
        );

        // 좌석 유저 변경
        seat.changeSeatUserId(input.getUserId());

        // 좌석 상태 변경.
        seat.changeStatusTemporary();

        // 좌석 만료 시간 변경.
        seat.changeExpiredAt(SystemClockHolder.plusFiveMinMillis());

    }

}
