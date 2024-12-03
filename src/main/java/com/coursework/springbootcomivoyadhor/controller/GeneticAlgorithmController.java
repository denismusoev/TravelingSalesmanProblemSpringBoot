package com.coursework.springbootcomivoyadhor.controller;

import com.coursework.springbootcomivoyadhor.model.Point;
import com.coursework.springbootcomivoyadhor.service.GeneticAlgorithmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genetic")
public class GeneticAlgorithmController {

    private final GeneticAlgorithmService geneticAlgorithmService;

    public GeneticAlgorithmController(GeneticAlgorithmService geneticAlgorithmService) {
        this.geneticAlgorithmService = geneticAlgorithmService;
    }

    @PostMapping("/optimize")
    public ResponseEntity<List<Point>> optimizeRoute(@RequestBody List<Point> points) {
        List<Point> optimalRoute = geneticAlgorithmService.findOptimalRoute(points);
        return ResponseEntity.ok(optimalRoute);
    }
}

