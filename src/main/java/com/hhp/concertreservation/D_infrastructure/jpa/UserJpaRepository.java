package com.hhp.concertreservation.D_infrastructure.jpa;

import com.hhp.concertreservation.C_domain.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {
}
