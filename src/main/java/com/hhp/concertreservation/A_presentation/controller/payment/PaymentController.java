package com.hhp.concertreservation.A_presentation.controller.payment;

import com.hhp.concertreservation.A_presentation.dto.payment.*;
import com.hhp.concertreservation.B_application.dto.payment.ConcertPaymentInput;
import com.hhp.concertreservation.B_application.dto.payment.ConcertPaymentOutput;
import com.hhp.concertreservation.B_application.facade.payment.PaymentFacade;
import com.hhp.concertreservation.B_application.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



@Tag(name = "결제")
@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentFacade paymentFacade;

    @Operation(summary = "결제", description = "콘서트 좌석을 예약확정과 포인트 차감 대기열 해제를 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "결제 성공", content = @Content(schema = @Schema(implementation = ConcertPaymentOutput.class)))
    })
    @PostMapping("/concert")
    public ResponseEntity<?> concertPayment(@RequestBody ConcertReservationPaymentReq req){

        ConcertPaymentInput input = ConcertPaymentInput.builder()
                .userId(req.getUserId())
                .token(req.getToken())
                .concertId(req.getConcertId())
                .concertDetailId(req.getConcertDetailId())
                .seatIds(req.getSeatIds())
                .amount(req.getAmount())
                .build();
        paymentFacade.concertPayment(input);

        return ResponseEntity.ok(null);
    }

}