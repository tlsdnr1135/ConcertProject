package com.hhp.concertreservation.C_domain.queue.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TOKEN")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(updatable = false, nullable = false, unique = true)
    private String token; //UUID

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "EXPIRED_DATE")
    private LocalDateTime expiredDate;

    @Builder
    public Token(Long id, String token, Long userId, LocalDateTime expiredDate) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.expiredDate = expiredDate;
    }
}