package com.hhp.concertreservation.D_infrastructure.repositoryImpl.payment;

import com.hhp.concertreservation.B_application.repository.payment.PaymentRepository;
import com.hhp.concertreservation.C_domain.order.Order;
import com.hhp.concertreservation.D_infrastructure.jpa.payment.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void save(Order order) {
        paymentJpaRepository.save(order);
    }

}
