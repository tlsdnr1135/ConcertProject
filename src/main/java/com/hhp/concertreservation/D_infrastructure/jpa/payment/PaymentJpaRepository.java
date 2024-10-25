package com.hhp.concertreservation.D_infrastructure.jpa.payment;

import com.hhp.concertreservation.C_domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<Order, Long> {
}
