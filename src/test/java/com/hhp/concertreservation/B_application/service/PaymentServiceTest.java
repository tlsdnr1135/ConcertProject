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
import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.OrderStatus;
import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import com.hhp.concertreservation.C_domain.order.Order;
import com.hhp.concertreservation.C_domain.point.Point;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Long userId = 1L;
        String tokenUUID = "123-124";
        Long concertId = 2L;
        Long concertDetailId = 5L;
        Long seatId = 6L;
        int amount = 1000;

        ConcertPaymentInput input = ConcertPaymentInput.builder()
                .userId(userId)
                .token(tokenUUID)
                .concertId(concertId)
                .concertDetailId(concertDetailId)
                .seatIds(seatId)
                .amount(amount)
                .build();

        Seat seat = Seat.builder()
                .id(seatId)
                .status(SeatStatus.TEMPORARY)
                .seatUserId(userId)
                .build();

        Point point = Point.builder()
                .id(userId)
                .point(amount)
                .build();

        Queue queue = Queue.builder()
                .concertId(concertId)
                .build();
        QueueItem queueItem = QueueItem.builder()
                .queueId(queue.getId())
                .token(tokenUUID)
                .build();

        Token token = Token.builder()
                .token(tokenUUID)
                .build();

        Order order = Order.builder()
                .userId(userId)
                .concertId(concertId)
                .concertDetailId(concertDetailId)
                .seatId(seatId)
                .amount(amount)
                .status(OrderStatus.PAYMENT)
                .build();

        when(seatRepository.findSeatsBySeatId(input.getSeatIds())).thenReturn(Optional.of(seat));
        when(pointRepository.findPointByUserId(input.getUserId())).thenReturn(Optional.of(point));
        when(queueRepository.findQueueByConcertId(input.getConcertId())).thenReturn(Optional.of(queue));
        when(queueItemRepository.findQueueItemByTokenAndQueueId(input.getToken(), queue.getId())).thenReturn(Optional.of(queueItem));
        when(tokenRepository.findTokenByToken(input.getToken())).thenReturn(Optional.of(token));

        //when
        paymentService.concertPayment(input);

        //then
        verify(tokenRepository, times(1)).deleteTokenByTokenId(queueItem.getId());
        verify(tokenRepository, times(1)).deleteTokenByTokenId(queueItem.getId());
        verify(paymentRepository, times(1)).save(any(Order.class));
    }
}