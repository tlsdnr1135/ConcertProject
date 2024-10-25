package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.concert.*;
import com.hhp.concertreservation.B_application.repository.concert.ConcertDetailRepository;
import com.hhp.concertreservation.B_application.repository.concert.ConcertRepository;
import com.hhp.concertreservation.B_application.repository.concert.SeatRepository;
import com.hhp.concertreservation.C_domain.concert.Concert;
import com.hhp.concertreservation.C_domain.concert.ConcertDetail;
import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import com.hhp.concertreservation.F_common.SystemClockHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @Mock
    SystemClockHolder systemClockHolder;

    @Mock
    ConcertRepository concertRepository;

    @Mock
    ConcertDetailRepository concertDetailRepository;

    @Mock
    SeatRepository seatRepository;

    @InjectMocks
    ConcertService concertService;

    @Test
    @DisplayName("성공_예약 가능한 날짜 조회")
    void success_selectConcertAvailableDates() {
        LocalDateTime temp1 = LocalDateTime.now();
        LocalDateTime temp2 = LocalDateTime.now().plusMinutes(5);

        //given
        ConcertAvailableDatesInput input = ConcertAvailableDatesInput.builder()
                .concertId(1L)
                .build();

        Concert concert = Concert.builder()
                .build();

        ConcertDetail seat1 = ConcertDetail.builder()
                .id(1L)
                .performanceDate(temp1)
                .maxSeatCount(10)
                .build();
        ConcertDetail seat2 = ConcertDetail.builder()
                .id(2L)
                .performanceDate(temp2)
                .maxSeatCount(20)
                .build();
        List<ConcertDetail> concertDetailList = List.of(seat1, seat2);

        when(concertRepository.findConcertByConcertId(input.getConcertId())).thenReturn(Optional.ofNullable(concert));
        when(concertDetailRepository.findConcertDetailByConcertId(concert.getId())).thenReturn(concertDetailList);


        //when
        ConcertAvailableDatesOutput output = concertService.selectConcertAvailableDates(input);

        //then
        assertEquals(seat1.getId(), output.getDateLists().get(0).getId());
        assertEquals(seat1.getPerformanceDate(), output.getDateLists().get(0).getDate());
        assertEquals(seat1.getMaxSeatCount(), output.getDateLists().get(0).getMaxSeatCnt());

        assertEquals(seat2.getId(), output.getDateLists().get(1).getId());
        assertEquals(seat2.getPerformanceDate(), output.getDateLists().get(1).getDate());
        assertEquals(seat2.getMaxSeatCount(), output.getDateLists().get(1).getMaxSeatCnt());

    }

    @Test
    @DisplayName("성공_좌석 조회")
    void success_selectAvailableSeats() {

        //given
        ConcertAvailableSeatsInput input = ConcertAvailableSeatsInput.builder()
                .concertDetailId(1L)
                .build();

        Seat seat1 = Seat.builder()
                .id(1L)
                .seatNumber("A-1")
                .status(SeatStatus.EMPTY)
                .build();
        Seat seat2 = Seat.builder()
                .id(2L)
                .seatNumber("B-2")
                .status(SeatStatus.TEMPORARY)
                .build();
        List<Seat> seatList = List.of(seat1, seat2);

        when(seatRepository.findSeatsByConcertDetailId(input.getConcertDetailId())).thenReturn(seatList);


        //when
        ConcertAvailableSeatOutput output = concertService.selectAvailableSeats(input);

        //then
        assertEquals(2 ,output.getTotalSeatCnt());
        assertEquals(1 ,output.getAvailableSeatCnt());

        assertEquals(seat1.getId() ,output.getSeatInfoList().get(0).getSeatId());
        assertEquals(seat1.getSeatNumber() ,output.getSeatInfoList().get(0).getSeatNumber());
        assertEquals(seat1.getStatus().toString() ,output.getSeatInfoList().get(0).getStatus());

    }

    @Test
    @DisplayName("성공_좌석 임시 예약")
    void success_temporarySeatsReservation() {

        //given
        TemporarySeatsReservationInput input = TemporarySeatsReservationInput.builder()
                .seatId(1L)
                .userId(10L)
                .build();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plusMinutes = LocalDateTime.now().plusMinutes(5);

        Seat seat = Seat.builder()
                .id(1L)
                .seatUserId(1L)
                .expiredTime(now)
                .status(SeatStatus.EMPTY)
                .build();

        when(systemClockHolder.plusFiveMinMillis()).thenReturn(plusMinutes);
        when(seatRepository.findSeatsBySeatIdAndStatus(input.getSeatId(), SeatStatus.EMPTY)).thenReturn(Optional.ofNullable(seat));


        //when
        concertService.temporarySeatsReservation(input);

        //then
        assertEquals(input.getUserId() ,seat.getSeatUserId());
        assertEquals(SeatStatus.TEMPORARY ,seat.getStatus());
        assertEquals(plusMinutes ,seat.getExpiredTime());

    }
}