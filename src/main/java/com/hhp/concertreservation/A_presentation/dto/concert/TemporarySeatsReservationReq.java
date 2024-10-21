package com.hhp.concertreservation.A_presentation.dto.concert;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TemporarySeatsReservationReq {

    private Long concertId;
    private String userId;
    private LocalDateTime reservationDate;

}
