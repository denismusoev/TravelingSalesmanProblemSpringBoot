package com.coursework.springbootcomivoyadhor.service;

import com.coursework.springbootcomivoyadhor.model.Point;
import com.coursework.springbootcomivoyadhor.repository.PointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public List<Point> getAllPoints() {
        return pointRepository.findAll();
    }

    public Point savePoint(Point point) {
        return pointRepository.save(point);
    }

    public void deletePoint(Long id) {
        pointRepository.deleteById(id);
    }
}
