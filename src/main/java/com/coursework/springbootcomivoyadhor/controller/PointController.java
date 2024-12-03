package com.coursework.springbootcomivoyadhor.controller;

import com.coursework.springbootcomivoyadhor.model.Point;
import com.coursework.springbootcomivoyadhor.service.PointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping
    public List<Point> getAllPoints() {
        return pointService.getAllPoints();
    }

    @PostMapping
    public ResponseEntity<Point> addPoint(@RequestBody Point point) {
        Point savedPoint = pointService.savePoint(point);
        return ResponseEntity.ok(savedPoint);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoint(@PathVariable Long id) {
        pointService.deletePoint(id);
        return ResponseEntity.noContent().build();
    }
}
