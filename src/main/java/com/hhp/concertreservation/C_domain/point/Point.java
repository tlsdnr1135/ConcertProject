package com.hhp.concertreservation.C_domain.point;

import com.hhp.concertreservation.C_domain.member.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "POINT")
public class Point {

    @Id
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @Column(name = "USER_ID")
    private User user;

    @Column(name = "POINT")
    private int point;

}
