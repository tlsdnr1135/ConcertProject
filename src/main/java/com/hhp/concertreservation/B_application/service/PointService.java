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

    /**
     * 포인트 조회
     */
    @Transactional
    public int selectPoints(Long userId) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(
                //TODO EXCEPTION
                ()-> new RuntimeException("해당하는 유저 없습니다.")
        );
        Point point = pointRepository.findPointByUserId(user.getId()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("유저에 해당하는 포인트가 없습니다.")
        );
        return point.getPoint();
    }

    /**
     * 포인트 충전
     */
    @Transactional
    public Point chargePoints(Long userId, int chargePoint) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(
                //TODO EXCEPTION
                ()-> new RuntimeException("해당하는 유저 없습니다.")
        );
        Point point = pointRepository.findPointByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("유저에 해당하는 포인트가 없습니다.")
        );

        point.chargePoint(chargePoint);

        Point save = pointRepository.save(point);

        return save;
    }

    /**
     * 포인트 차감
     */
    public Point deductionPoint(Long userId, int amount) {
        Point point = pointRepository.findPointByUserId(userId).orElseThrow(
                () -> new RuntimeException("해당하는 유저가 없습니다.")
        );
        point.deductionPoint(amount);

        Point save = pointRepository.save(point);

        return save;
    }

}
