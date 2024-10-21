package com.hhp.concertreservation.D_infrastructure.repositoryImpl;

import com.hhp.concertreservation.B_application.repository.UserRepository;
import com.hhp.concertreservation.C_domain.member.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserByUserId(UUID userId) {
        return null;
    }
}
