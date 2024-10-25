package com.hhp.concertreservation.B_application.repository.point;

import com.hhp.concertreservation.C_domain.point.Point;

import java.util.Optional;

public interface PointRepository {

    Optional<Point> findPointByUserId(Long userId);
    Point save(Point point);
}
