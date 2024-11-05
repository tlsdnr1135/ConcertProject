package com.hhp.concertreservation.B_application.facade.point;

import com.hhp.concertreservation.B_application.dto.point.ChargePointsInput;
import com.hhp.concertreservation.B_application.dto.point.ChargePointsOutput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsInput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsOutput;
import com.hhp.concertreservation.B_application.service.PointService;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointFacade {

    private final PointService pointService;

    /**
     * 잔액 조회
     */
    public SelectPointsOutput selectPoints(SelectPointsInput input) {

        int points = pointService.selectPoints(input.getUserId());

        SelectPointsOutput output = SelectPointsOutput.builder()
                .point(points)
                .build();
        return output;
    }

    /**
     * 잔액 충전
     */
    public ChargePointsOutput chargePoints(ChargePointsInput input) {

        int points = pointService.chargePoints(input.getUserId(), input.getChargePoint()).getPoint();

        ChargePointsOutput output = ChargePointsOutput.builder()
                .point(points)
                .build();
        return output;
    }

}
