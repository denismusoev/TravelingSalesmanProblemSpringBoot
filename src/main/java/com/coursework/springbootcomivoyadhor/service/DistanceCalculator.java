package com.coursework.springbootcomivoyadhor.service;
import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6371; // Радиус Земли в километрах

    public double calculateDistance(Point p1, Point p2) {
        double latDistance = Math.toRadians(p2.getLatitude() - p1.getLatitude());
        double lonDistance = Math.toRadians(p2.getLongitude() - p1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(p1.getLatitude())) * Math.cos(Math.toRadians(p2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // Возвращает расстояние в километрах
    }
}

