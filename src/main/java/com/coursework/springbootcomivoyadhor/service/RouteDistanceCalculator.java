package com.coursework.springbootcomivoyadhor.service;

import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RouteDistanceCalculator {

    private final DistanceCalculator distanceCalculator;

    public RouteDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public double calculateTotalDistance(List<List<Point>> clusters) {
        double totalDistance = 0;

        // Переменная для хранения последней точки предыдущего кластера
        Point previousPoint = null;

        for (int clusterIndex = 0; clusterIndex < clusters.size(); clusterIndex++) {
            List<Point> cluster = clusters.get(clusterIndex);

            // Рассчитываем расстояние внутри текущего кластера
            for (int i = 0; i < cluster.size() - 1; i++) {
                totalDistance += distanceCalculator.distance(cluster.get(i), cluster.get(i + 1));
            }

            // Если есть предыдущая точка (переход между кластерами)
            if (previousPoint != null) {
                totalDistance += distanceCalculator.distance(previousPoint, cluster.get(0));
            }

            // Обновляем последнюю точку текущего кластера
            previousPoint = cluster.get(cluster.size() - 1);
        }

        // Возвращаемся к начальной точке из первого кластера, если есть кластеры
        if (!clusters.isEmpty() && !clusters.get(0).isEmpty() && previousPoint != null) {
            totalDistance += distanceCalculator.distance(previousPoint, clusters.get(0).get(0));
        }

        return totalDistance;
    }

}

