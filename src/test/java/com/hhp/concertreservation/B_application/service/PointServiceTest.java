package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.point.ChargePointsInput;
import com.hhp.concertreservation.B_application.dto.point.ChargePointsOutput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsInput;
import com.hhp.concertreservation.B_application.dto.point.SelectPointsOutput;
import com.hhp.concertreservation.B_application.repository.point.PointRepository;
import com.hhp.concertreservation.B_application.repository.UserRepository;
import com.hhp.concertreservation.C_domain.member.User;
import com.hhp.concertreservation.C_domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @Mock
    PointRepository pointRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    PointService pointService;

    @Test
    @DisplayName("성공_포인트 조회")
    void success_selectPoints() {
        //given
        SelectPointsInput input = SelectPointsInput.builder()
                .userId(1L)
                .build();

        User user = User.builder()
                .build();

        Point point = Point.builder()
                .point(1000)
                .build();

        SelectPointsOutput expectOutput = SelectPointsOutput.builder()
                .point(1000)
                .build();

        when(userRepository.findUserByUserId(input.getUserId())).thenReturn(Optional.of(user));
        when(pointRepository.findPointByUserId(user.getId())).thenReturn(Optional.of(point));


        //when
        SelectPointsOutput output = pointService.selectPoints(input);

        //then
        assertEquals(expectOutput.getPoint(), output.getPoint());

    }

    @Test
    @DisplayName("성공_포인트 충전")
    void success_chargePoints() {
        //given
        ChargePointsInput input = ChargePointsInput.builder()
                .userId(1L)
                .chargePoint(50000)
                .build();

        User user = User.builder()
                .id(1L)
                .build();

        Point point = Point.builder()
                .point(1000)
                .build();

        SelectPointsOutput expectOutput = SelectPointsOutput.builder()
                .point(51000)
                .build();

        when(userRepository.findUserByUserId(input.getUserId())).thenReturn(Optional.of(user));
        when(pointRepository.findPointByUserId(user.getId())).thenReturn(Optional.of(point));


        //when
        ChargePointsOutput output = pointService.chargePoints(input);

//        ArgumentCaptor<Point> argumentCaptor = ArgumentCaptor.forClass(Point.class);
//        verify(pointRepository, times(1)).save(argumentCaptor.capture());

        //then
        assertEquals(expectOutput.getPoint(), output.getPoint());

//        System.out.println("=========================");
//        System.out.println(argumentCaptor.getValue());

    }
}