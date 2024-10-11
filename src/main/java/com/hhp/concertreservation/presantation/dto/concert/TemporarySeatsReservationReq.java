package com.hhp.concertreservation.presantation.dto.concert;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TemporarySeatsReservationReq {

    private Long concertId;
    private String userId;
    private LocalDateTime reservationDate;

}
