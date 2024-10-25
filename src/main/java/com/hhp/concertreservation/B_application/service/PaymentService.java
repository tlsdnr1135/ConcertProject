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

    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
    private final PointRepository pointRepository;
    private final QueueItemRepository queueItemRepository;
    private final QueueRepository queueRepository;
    private final TokenRepository tokenRepository;
    private final PaymentRepository paymentRepository;


    /**
     * 결제
     */
    @Transactional
    public void concertPayment(ConcertPaymentInput input) {

        //좌석 상태 변경 -> COMPLETE
        Seat seat = seatRepository.findSeatsBySeatId(input.getSeatIds()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("좌석이 존재하지 않습니다.")
        );
        seat.changeStatusComplete();
        seat.changeSeatUserId(input.getUserId());

        //포인트 차감
        Point point = pointRepository.findPointByUserId(input.getUserId()).orElseThrow(
                () -> new RuntimeException("해당하는 유저가 없습니다.")
        );
        point.deductionPoint(input.getAmount());

        //대기열 삭제
        Queue queue = queueRepository.findQueueByConcertId(input.getConcertId()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 대기열이 없습니다.")
        );
        QueueItem queueItem = queueItemRepository.findQueueItemByTokenAndQueueId(input.getToken(), queue.getId()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당 대기열이 존재하지 않습니다.")
        );
        queueItemRepository.deleteQueueItemById(queueItem.getId());

        //토큰 삭제
        //삭제할 토큰이 있는지 확인.
        Token token = tokenRepository.findTokenByToken(input.getToken()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 토큰이 없습니다.")
        );
        tokenRepository.deleteTokenByTokenId(token.getId());

        //주문 save
        Order order = Order.builder()
                .userId(input.getUserId())
                .concertId(input.getConcertId())
                .concertDetailId(input.getConcertDetailId())
                .seatId(input.getSeatIds())
                .amount(input.getAmount())
                .status(OrderStatus.PAYMENT)
                .build();

        paymentRepository.save(order);
    }

}