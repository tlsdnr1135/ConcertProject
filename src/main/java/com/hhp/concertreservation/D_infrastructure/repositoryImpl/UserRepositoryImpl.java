package com.hhp.concertreservation.D_infrastructure.repositoryImpl;

import com.hhp.concertreservation.B_application.repository.UserRepository;
import com.hhp.concertreservation.C_domain.member.User;
import com.hhp.concertreservation.D_infrastructure.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findUserByUserId(Long userId) {
        return userJpaRepository.findById(userId);
    }
}
