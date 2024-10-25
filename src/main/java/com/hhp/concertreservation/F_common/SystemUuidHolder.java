package com.hhp.concertreservation.F_common;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SystemUuidHolder {

    public String random() {
        return UUID.randomUUID().toString();
    }

}