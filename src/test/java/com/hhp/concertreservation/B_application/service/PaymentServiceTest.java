package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.payment.ConcertPaymentInput;
import com.hhp.concertreservation.B_application.repository.concert.ConcertDetailRepository;
import com.hhp.concertreservation.B_application.repository.concert.ConcertRepository;
import com.hhp.concertreservation.B_application.repository.concert.SeatRepository;
import com.hhp.concertreservation.B_application.repository.payment.PaymentRepository;
import com.hhp.concertreservation.B_application.repository.point.PointRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.B_application.repository.queue.TokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    SeatRepository seatRepository;
    @Mock
    PointRepository pointRepository;
    @Mock
    QueueItemRepository queueItemRepository;
    @Mock
    QueueRepository queueRepository;
    @Mock
    TokenRepository tokenRepository;
    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentService paymentService;

    @Test
    @DisplayName("성공_결제")
    void success_concertPayment() {
        //given
        ConcertPaymentInput input = ConcertPaymentInput.builder()
                .userId(1L)
                .token("123-124")
                .concertId(2L)
                .concertDetailId(5L)
                .seatIds(6L)
                .amount(1000)
                .build();

//        when(seatRepository.findSeatsBySeatId(input.getSeatIds())).thenReturn();
//        when(pointRepository.findPointByUserId(input.getUserId())).thenReturn();
//        when(queueRepository.findQueueByConcertId(input.getConcertId())).thenReturn();
//        when(queueItemRepository.findQueueItemByTokenAndQueueId(input.getToken(), queue.getId())).thenReturn();
//        when(tokenRepository.findTokenByToken(input.getToken())).thenReturn();
//        when().thenReturn();
//        when().thenReturn();
//        when().thenReturn();
//        when().thenReturn();

        //when
//        paymentService.concertPayment(input);


        //then


    }
}