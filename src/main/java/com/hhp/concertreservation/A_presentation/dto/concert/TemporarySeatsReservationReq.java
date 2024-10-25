package com.hhp.concertreservation.A_presentation.dto.concert;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TemporarySeatsReservationReq {

    private Long seatId;
    private Long userId;

}
