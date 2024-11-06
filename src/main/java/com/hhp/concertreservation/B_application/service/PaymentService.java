package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.payment.ConcertPaymentInput;
import com.hhp.concertreservation.B_application.dto.payment.ConcertPaymentOutput;
import com.hhp.concertreservation.B_application.repository.concert.ConcertRepository;
import com.hhp.concertreservation.B_application.repository.concert.SeatRepository;
import com.hhp.concertreservation.B_application.repository.payment.PaymentRepository;
import com.hhp.concertreservation.B_application.repository.point.PointRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.B_application.repository.queue.TokenRepository;
import com.hhp.concertreservation.C_domain.concert.Seat;
import com.hhp.concertreservation.C_domain.enums.OrderStatus;
import com.hhp.concertreservation.C_domain.order.Order;
import com.hhp.concertreservation.C_domain.point.Point;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;


    /**
     * 주문
     */
    @Transactional
    public void saveOrder(Long userId, Long concertId, Long concertDetailId, Long seatId, int amount) {
        //주문 save
        Order order = Order.builder()
                .userId(userId)
                .concertId(concertId)
                .concertDetailId(concertDetailId)
                .seatId(seatId)
                .amount(amount)
                .status(OrderStatus.PAYMENT)
                .build();

        paymentRepository.save(order);
    }

}