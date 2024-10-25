package com.hhp.concertreservation.C_domain.point;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "POINT_HISTORY")
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "POINT")
    private int point;

    @Column(name = "TYPE")
    private String type;

}