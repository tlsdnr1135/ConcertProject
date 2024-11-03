package com.hhp.concertreservation.B_application.facade.payment;

import com.hhp.concertreservation.B_application.dto.payment.ConcertPaymentInput;
import com.hhp.concertreservation.B_application.service.ConcertService;
import com.hhp.concertreservation.B_application.service.PaymentService;
import com.hhp.concertreservation.B_application.service.PointService;
import com.hhp.concertreservation.B_application.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentFacade {

    private final PaymentService paymentService;
    private final ConcertService concertService;
    private final PointService pointService;
    private final QueueService queueService;

    /**
     * 결제
     */
    public void concertPayment(ConcertPaymentInput input) {

        concertService.completeSeatsReservation(input.getSeatIds());
        pointService.deductionPoint(input.getUserId(), input.getAmount());
        queueService.cancelQueue(input.getConcertId(), input.getToken());
        paymentService.saveOrder(input.getUserId(), input.getConcertId(),
                input.getConcertDetailId(), input.getSeatIds(), input.getAmount());

    }


}
