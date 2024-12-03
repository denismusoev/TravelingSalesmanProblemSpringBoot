package com.coursework.springbootcomivoyadhor.controller;

import com.coursework.springbootcomivoyadhor.model.Point;
import com.coursework.springbootcomivoyadhor.service.RouteDistanceCalculator;
import com.coursework.springbootcomivoyadhor.service.RouteOptimizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/routes")
public class RouteOptimizationController {

    private final RouteOptimizationService routeOptimizationService;
    private final RouteDistanceCalculator routeDistanceCalculator;

    public RouteOptimizationController(RouteOptimizationService routeOptimizationService,
                                       RouteDistanceCalculator routeDistanceCalculator) {
        this.routeOptimizationService = routeOptimizationService;
        this.routeDistanceCalculator = routeDistanceCalculator;
    }

    @PostMapping("/final-optimize/{k}")
    public ResponseEntity<Map<String, Object>> getFinalOptimizedRoute(@RequestBody List<Point> points, @PathVariable int k) {
        List<List<Point>> optimizedRoute = routeOptimizationService.getFinalOptimizedRouteByClusters(points, k);
        double totalDistance = routeDistanceCalculator.calculateTotalDistance(optimizedRoute);

        Map<String, Object> response = new HashMap<>();
        response.put("optimizedRoute", optimizedRoute);
        response.put("totalDistance", totalDistance);

        return ResponseEntity.ok(response);
    }
}

