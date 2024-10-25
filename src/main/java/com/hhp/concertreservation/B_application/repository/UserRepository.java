package com.hhp.concertreservation.B_application.repository;

import com.hhp.concertreservation.C_domain.member.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUserByUserId(Long userId);

}
