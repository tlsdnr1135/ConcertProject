package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.point.ChargePointsInput;
import com.hhp.concertreservation.B_application.dto.point.ChargePointsOutput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsInput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsOutput;
import com.hhp.concertreservation.B_application.repository.point.PointRepository;
import com.hhp.concertreservation.B_application.repository.UserRepository;
import com.hhp.concertreservation.C_domain.member.User;
import com.hhp.concertreservation.C_domain.point.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    @Transactional
    public SelectPointsOutput selectPoints(SelectPointsInput input) {
        User user = userRepository.findUserByUserId(input.getUserId()).orElseThrow(
                //TODO EXCEPTION
                ()-> new RuntimeException("해당하는 유저 없습니다.")
        );
        Point point = pointRepository.findPointByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("유저에 해당하는 포인트가 없습니다.")
        );

        SelectPointsOutput output = SelectPointsOutput.builder()
                .point(point.getPoint())
                .build();
        return output;
    }

    @Transactional
    public ChargePointsOutput chargePoints(ChargePointsInput input) {
        User user = userRepository.findUserByUserId(input.getUserId()).orElseThrow(
                //TODO EXCEPTION
                ()-> new RuntimeException("해당하는 유저 없습니다.")
        );
        Point point = pointRepository.findPointByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("유저에 해당하는 포인트가 없습니다.")
        );

        point.chargePoint(input.getChargePoint());

//        pointRepository.save(point);

        return ChargePointsOutput.builder()
                .point(point.getPoint())
                .build();
    }


}
