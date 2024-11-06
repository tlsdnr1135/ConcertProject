package com.hhp.concertreservation.A_presentation.controller.point;

import com.hhp.concertreservation.A_presentation.dto.payment.ChargePointsReq;
import com.hhp.concertreservation.B_application.dto.point.ChargePointsOutput;
import com.hhp.concertreservation.B_application.dto.point.ChargePointsInput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsInput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsOutput;
import com.hhp.concertreservation.B_application.facade.point.PointFacade;
import com.hhp.concertreservation.B_application.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "포인트")
@RequiredArgsConstructor
@RestController
@RequestMapping("/points")
public class PointController {

    private final PointFacade pointFacade;

    @Operation(summary = "잔액 조회", description = "유저의 포인트를 조회한다.")
    @GetMapping("/point/{userId}")
    public ResponseEntity<SelectPointsOutput> selectPoints(@PathVariable("userId") Long userId){

        SelectPointsInput input = SelectPointsInput.builder().userId(userId).build();

        SelectPointsOutput output = pointFacade.selectPoints(input);

        return ResponseEntity.ok(output);
    }

    @Operation(summary = "잔액 충전", description = "유저의 포인트를 충전한다.")
    @PostMapping("/point")
    public ResponseEntity<ChargePointsOutput> chargePoints(@RequestBody ChargePointsReq req){

        ChargePointsInput input = ChargePointsInput.builder()
                .userId(req.getUserId())
                .chargePoint(req.getChargePoint())
                .build();

        ChargePointsOutput output = pointFacade.chargePoints(input);

        return ResponseEntity.ok(output);
    }

}