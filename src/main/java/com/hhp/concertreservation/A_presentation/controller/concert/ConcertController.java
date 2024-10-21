package com.hhp.concertreservation.A_presentation.controller.concert;

import com.hhp.concertreservation.A_presentation.dto.concert.CheckAvailableDatesRes;
import com.hhp.concertreservation.A_presentation.dto.concert.CheckAvailableDatesRes.AvailableDateAndSeat;
import com.hhp.concertreservation.A_presentation.dto.concert.CheckAvailableSeatsRes;
import com.hhp.concertreservation.A_presentation.dto.concert.CheckAvailableSeatsRes.seatInfo;
import com.hhp.concertreservation.A_presentation.dto.concert.TemporarySeatsReservationReq;
import com.hhp.concertreservation.A_presentation.dto.concert.TemporarySeatsReservationRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Tag(name = "콘서트")
@RestController
@RequestMapping("/concerts")
public class ConcertController {

    @Operation(summary = "예약 가능한 날짜 조회", description = "예약 가능한 날짜를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CheckAvailableDatesRes.class)))
    })
    @GetMapping("/check-available-dates/{concertId}")
    public ResponseEntity<CheckAvailableDatesRes> CheckAvailableDates(@PathVariable("concertId") Long concertId){


        AvailableDateAndSeat availableDateAndSeat1 = AvailableDateAndSeat.builder()
                .date(new Timestamp(Calendar.getInstance().getTimeInMillis()))
                .seatCnt(10)
                .build();
        AvailableDateAndSeat availableDateAndSeat2 = AvailableDateAndSeat.builder()
                .date(new Timestamp(Calendar.getInstance().getTimeInMillis()))
                .seatCnt(20)
                .build();
        List<AvailableDateAndSeat> dateLists = List.of(availableDateAndSeat1, availableDateAndSeat2);

        CheckAvailableDatesRes checkAvailableDatesRes = CheckAvailableDatesRes.builder()
                .dateLists(dateLists)
                .build();

        return ResponseEntity.ok(checkAvailableDatesRes);
    }

    @Operation(summary = "좌석 조회", description = "좌석을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CheckAvailableSeatsRes.class)))
    })
    @GetMapping("/check-available-seats/{concertId}")
    public ResponseEntity<CheckAvailableSeatsRes> CheckAvailableSeats(@PathVariable("concertId") Long concertId, @RequestParam("date") Timestamp date, @RequestParam("userId") LocalDateTime userId){

        seatInfo seatInfo1 = seatInfo.builder()
                .seatId(1L)
                .seatNumber("A-1")
                .status("empty")
                .build();

        seatInfo seatInfo2 = seatInfo.builder()
                .seatId(2L)
                .seatNumber("B-1")
                .status("temporary")
                .build();
        List<seatInfo> dateLists = List.of(seatInfo1, seatInfo2);

        CheckAvailableSeatsRes result = CheckAvailableSeatsRes.builder()
                .totalSeatCnt(2)
                .availableSeatCnt(1)
                .seatInfoList(dateLists)
                .build();

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "좌석 임시 예약", description = "좌석을 임시 예약한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "예약 성공", content = @Content(schema = @Schema(implementation = TemporarySeatsReservationRes.class)))
    })
    @PostMapping("/reservation-temporary")
    public ResponseEntity<TemporarySeatsReservationRes> TemporarySeatsReservation(@RequestBody TemporarySeatsReservationReq req){

        TemporarySeatsReservationRes result = TemporarySeatsReservationRes.builder()
                .reservationSeatList(List.of(1,2,5,6))
                .build();

        return ResponseEntity.ok(result);
    }

}
