package com.hhp.concertreservation.C_domain.queue;


import com.hhp.concertreservation.C_domain.member.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TOKEN")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "EXPIRED_DATE")
    private LocalDateTime expiredDate;

    @Builder
    public Token(Long id, User user, LocalDateTime expiredDate) {
        this.id = id;
        this.user = user;
        this.expiredDate = expiredDate;
    }

}
