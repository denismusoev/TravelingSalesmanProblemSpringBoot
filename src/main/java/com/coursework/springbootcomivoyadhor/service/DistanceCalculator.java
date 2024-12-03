package com.coursework.springbootcomivoyadhor.service;
import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6371; // Радиус Земли в километрах

    public double getRouteDistance(List<Point> route) {
        double totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += distance(route.get(i), route.get(i + 1));
        }
        totalDistance += distance(route.get(route.size() - 1), route.get(0)); // Замыкаем маршрут
        return totalDistance;
    }

    public double distance(Point p1, Point p2) {
        double latDiff = p1.getLatitude() - p2.getLatitude();
        double lonDiff = p1.getLongitude() - p2.getLongitude();
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }
}

