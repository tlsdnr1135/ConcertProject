package com.hhp.concertreservation.B_application.repository.payment;


import com.hhp.concertreservation.C_domain.order.Order;

public interface PaymentRepository {
    void save(Order order);
}