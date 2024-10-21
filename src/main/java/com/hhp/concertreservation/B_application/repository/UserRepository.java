package com.hhp.concertreservation.B_application.repository;

import com.hhp.concertreservation.C_domain.member.User;

import java.util.UUID;

public interface UserRepository {

    User findUserByUserId(UUID userId);

}
