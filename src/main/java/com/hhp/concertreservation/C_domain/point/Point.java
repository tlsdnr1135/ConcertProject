package com.hhp.concertreservation.C_domain.point;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "POINT")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "POINT")
    private int point;

    @Builder
    public Point(Long id, Long userId, int point) {
        this.id = id;
        this.userId = userId;
        this.point = point;
    }


    public void chargePoint(int point) {
        if(this.point + point <= 0) {
            //TODO EXCEPTION
            throw new RuntimeException("포인트가 이상하다.!");
        }
        this.point += point;
    }

    public void deductionPoint(int point) {
        if(this.point - point <= 0) {
            //TODO EXCEPTION
            throw new RuntimeException("포인트가 이상하다.!");
        }
        this.point += point;
    }


}
