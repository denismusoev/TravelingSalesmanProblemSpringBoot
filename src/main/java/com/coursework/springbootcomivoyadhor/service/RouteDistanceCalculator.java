package com.coursework.springbootcomivoyadhor.service;

import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteDistanceCalculator {

    private final DistanceCalculator distanceCalculator;

    public RouteDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public double calculateTotalDistance(List<Point> route) {
        double totalDistance = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += distanceCalculator.calculateDistance(route.get(i), route.get(i + 1));
        }

        // Добавляем расстояние для возврата в начальную точку
        totalDistance += distanceCalculator.calculateDistance(route.get(route.size() - 1), route.get(0));

        return totalDistance;
    }
}

