package com.hhp.concertreservation.F_common;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class SystemClockHolder {

    public LocalDateTime millis() {
        return LocalDateTime.now(Clock.systemDefaultZone());
    }

}
