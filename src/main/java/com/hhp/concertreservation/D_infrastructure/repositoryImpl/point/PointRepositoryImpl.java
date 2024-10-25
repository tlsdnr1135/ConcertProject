package com.hhp.concertreservation.D_infrastructure.repositoryImpl.point;

import com.hhp.concertreservation.B_application.repository.point.PointRepository;
import com.hhp.concertreservation.C_domain.point.Point;
import com.hhp.concertreservation.D_infrastructure.jpa.point.PointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PointRepositoryImpl implements PointRepository {

    private final PointJpaRepository pointJpaRepository;

    @Override
    public Optional<Point> findPointByUserId(Long userId) {
        Optional<Point> pointOp = pointJpaRepository.findByUserId(userId);
        return pointOp;
    }

    @Override
    public Point save(Point point) {
        return pointJpaRepository.save(point);
    }
}
