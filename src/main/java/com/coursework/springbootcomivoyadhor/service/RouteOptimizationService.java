package com.coursework.springbootcomivoyadhor.service;

import com.coursework.springbootcomivoyadhor.model.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteOptimizationService {

    private static final Logger logger = LoggerFactory.getLogger(RouteOptimizationService.class);

    private final ClusteringService clusteringService;
    private final GeneticAlgorithmService geneticAlgorithmService;

    public RouteOptimizationService(ClusteringService clusteringService, GeneticAlgorithmService geneticAlgorithmService) {
        this.clusteringService = clusteringService;
        this.geneticAlgorithmService = geneticAlgorithmService;
    }

    public List<Point> getFinalOptimizedRoute(List<Point> points, int k) {
        logger.info("Начало оптимизации маршрута. Количество точек: {}, Количество кластеров: {}", points.size(), k);

        // 1. Кластеризация
        List<List<Point>> clusters = clusteringService.performClustering(points, k);
        logger.info("Кластеризация завершена. Количество кластеров: {}", clusters.size());

        List<Point> finalRoute = new ArrayList<>();
        Point startingPoint = null;  // Определим позже

        // 2. Оптимизация маршрутов в кластерах
        for (int i = 0; i < clusters.size(); i++) {
            List<Point> cluster = clusters.get(i);
            logger.info("Оптимизация маршрута для кластера {}. Количество точек в кластере: {}", i + 1, cluster.size());

            List<Point> optimizedClusterRoute = geneticAlgorithmService.findOptimalRoute(cluster);
            logger.info("Оптимизация кластера {} завершена. Оптимальный маршрут: {}", i + 1, optimizedClusterRoute);

            if (startingPoint == null) {
                startingPoint = optimizedClusterRoute.get(0);  // Устанавливаем начальную точку только один раз
            }

            finalRoute.addAll(optimizedClusterRoute);
        }

        // 3. Возвращение к начальной точке маршрута, определенной в optimizedClusterRoute
        if (startingPoint != null) {
            finalRoute.add(startingPoint);
            logger.info("Возвращение к начальной точке маршрута. Итоговый маршрут замкнут: {}", finalRoute);
        }

        return finalRoute;
    }
}

