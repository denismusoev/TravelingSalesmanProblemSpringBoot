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

    public List<List<Point>> getFinalOptimizedRouteByClusters(List<Point> points, int k) {
        logger.info("Начало оптимизации маршрута. Количество точек: {}, Количество кластеров: {}", points.size(), k);

        // 1. Кластеризация
        List<List<Point>> clusters = clusteringService.performClustering(points, k);
        logger.info("Кластеризация завершена. Количество кластеров: {}", clusters.size());

        List<List<Point>> optimizedRoutesByCluster = new ArrayList<>();

        // 2. Оптимизация маршрутов в каждом кластере
        for (int i = 0; i < clusters.size(); i++) {
            List<Point> cluster = clusters.get(i);
            logger.info("Оптимизация маршрута для кластера {}. Количество точек в кластере: {}", i + 1, cluster.size());

            List<Point> optimizedClusterRoute = geneticAlgorithmService.findOptimalRoute(cluster);
            logger.info("Оптимизация кластера {} завершена. Оптимальный маршрут: {}", i + 1, optimizedClusterRoute);

            // Добавляем оптимизированный маршрут для данного кластера в итоговый список
            optimizedRoutesByCluster.add(optimizedClusterRoute);
        }

        logger.info("Оптимизация маршрутов завершена. Маршруты по кластерам: {}", optimizedRoutesByCluster);

        return optimizedRoutesByCluster;
    }
}

