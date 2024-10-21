package com.hhp.concertreservation.A_presentation.controller.payment;

import com.hhp.concertreservation.A_presentation.dto.payment.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Tag(name = "결제")
@RestController
@RequestMapping("/payment")
public class PointController {

    @Operation(summary = "잔액 조회", description = "유저의 포인트를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = SelectPointsRes.class)))
    })
    @GetMapping("/point/{userId}")
    public ResponseEntity<SelectPointsRes> SelectPoints(@PathVariable("userId") String userId){

        SelectPointsRes result = SelectPointsRes.builder()
                .point(1000)
                .build();

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "잔액 충전", description = "유저의 포인트를 충전한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "충전 성공", content = @Content(schema = @Schema(implementation = ChargePointsRes.class)))
    })
    @PostMapping("/point")
    public ResponseEntity<ChargePointsRes> ChargePoints(@RequestBody ChargePointsReq req){

        ChargePointsRes result = ChargePointsRes.builder()
                .point(2000)
                .build();

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "결제", description = "콘서트 좌석을 예약확정과 포인트 차감 대기열 해제를 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "결제 성공", content = @Content(schema = @Schema(implementation = ConcertReservationPaymentRes.class)))
    })
    @PostMapping("/concert-seats")
    public ResponseEntity<ConcertReservationPaymentRes> ConcertReservationPayment(@RequestBody ConcertReservationPaymentReq req){

        ConcertReservationPaymentRes result = ConcertReservationPaymentRes.builder()
                .userId("wook-shin")
                .concertId(1L)
                .reservationDate(LocalDateTime.now())
                .seatIds(List.of(1,2,3))
                .usePoint(2000)
                .point(0)
                .build();

        return ResponseEntity.ok(result);
    }

}
