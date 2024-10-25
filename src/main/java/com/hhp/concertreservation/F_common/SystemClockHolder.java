package com.hhp.concertreservation.F_common;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class SystemClockHolder {

    public LocalDateTime nowMillis() {
        return LocalDateTime.now();
    }

    public LocalDateTime plusFiveMinMillis() {
        return LocalDateTime.now().plusMinutes(5);
    }

}
