package com.coursework.springbootcomivoyadhor.controller;

import com.coursework.springbootcomivoyadhor.model.Point;
import com.coursework.springbootcomivoyadhor.service.ClusteringService;
import com.coursework.springbootcomivoyadhor.service.PointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clustering")
public class ClusteringController {

    private final ClusteringService clusteringService;
    private final PointService pointService;

    public ClusteringController(ClusteringService clusteringService, PointService pointService) {
        this.clusteringService = clusteringService;
        this.pointService = pointService;
    }

    @GetMapping("/{k}")
    public ResponseEntity<List<List<Point>>> getClusters(@PathVariable int k) {
        List<Point> points = pointService.getAllPoints();
        List<List<Point>> clusters = clusteringService.performClustering(points, k);
        return ResponseEntity.ok(clusters);
    }
}
