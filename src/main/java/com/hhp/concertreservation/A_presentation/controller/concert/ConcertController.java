package com.hhp.concertreservation.A_presentation.controller.concert;

import com.hhp.concertreservation.B_application.dto.concert.*;
import com.hhp.concertreservation.B_application.dto.concert.ConcertAvailableSeatOutput.SeatInfo;
import com.hhp.concertreservation.A_presentation.dto.concert.TemporarySeatsReservationReq;
import com.hhp.concertreservation.A_presentation.dto.concert.TemporarySeatsReservationRes;
import com.hhp.concertreservation.B_application.facade.concert.ConcertFacade;
import com.hhp.concertreservation.B_application.service.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "콘서트")
@RequiredArgsConstructor
@RestController
@RequestMapping("/concerts")
public class ConcertController {

    private final ConcertFacade concertFacade;

    @Operation(summary = "예약 가능한 날짜 조회", description = "예약 가능한 날짜를 조회한다.")
    @GetMapping("/available-dates/{concertId}")
    public ResponseEntity<ConcertAvailableDatesOutput> selectConcertAvailableDates(@PathVariable("concertId") Long concertId){

        ConcertAvailableDatesInput input = ConcertAvailableDatesInput.builder()
                .concertId(concertId)
                .build();

        ConcertAvailableDatesOutput checkAvailableDatesRes = concertFacade.selectConcertAvailableDates(input);

        return ResponseEntity.ok(checkAvailableDatesRes);
    }

    @Operation(summary = "좌석 조회", description = "좌석을 조회한다.")
    @GetMapping("/available-seats/{concertDetailId}")
    public ResponseEntity<ConcertAvailableSeatOutput> selectConcertAvailableSeats(@PathVariable("concertDetailId") Long concertDetailId){

        ConcertAvailableSeatsInput input = ConcertAvailableSeatsInput.builder()
                .concertDetailId(concertDetailId)
                .build();

        ConcertAvailableSeatOutput output = concertFacade.selectAvailableSeats(input);

        return ResponseEntity.ok(output);
    }

    @Operation(summary = "좌석 임시 예약", description = "좌석을 임시 예약한다.")
    @PostMapping("/reservation-temporary")
    public ResponseEntity<Void> TemporarySeatsReservation(@RequestBody TemporarySeatsReservationReq req){

        TemporarySeatsReservationInput input = TemporarySeatsReservationInput.builder()
                .userId(req.getUserId())
                .seatId(req.getSeatId())
                .build();

        concertFacade.temporarySeatsReservation(input);

        return ResponseEntity.ok(null);
    }

}
