package com.hhp.concertreservation.C_domain.concert;

import com.hhp.concertreservation.C_domain.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "SEAT")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONCERT_DETAIL_ID")
    private Long concertDetailId;

    @Column(name = "SEAT_USER_ID")
    private Long seatUserId;

    @Column(name = "SEAT_NUMBER")
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private SeatStatus status;

    @Column(name = "EXPIRED_TIME")
    private LocalDateTime expiredTime;

    @Builder
    public Seat(Long id, Long concertDetailId, Long seatUserId, String seatNumber, SeatStatus status, LocalDateTime expiredTime) {
        this.id = id;
        this.concertDetailId = concertDetailId;
        this.seatUserId = seatUserId;
        this.seatNumber = seatNumber;
        this.status = status;
        this.expiredTime = expiredTime;
    }

    public void changeSeatUserId(Long seatUserId){
        this.seatUserId = seatUserId;
    }

    public void changeExpiredAt(LocalDateTime expiredTime){
        this.expiredTime = expiredTime;
    }

    public void changeStatusTemporary() {
        this.status = SeatStatus.TEMPORARY;
    }
    public void changeStatusComplete() {
        this.status = SeatStatus.COMPLETE;
    }

}
