package com.coursework.springbootcomivoyadhor.service;

import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.stereotype.Service;
import smile.clustering.KMeans;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClusteringService {

    public List<List<Point>> performClustering(List<Point> points, int k) {
        if (points.size() < k) {
            throw new IllegalArgumentException("Количество кластеров не может превышать количество точек.");
        }

        // Преобразуем точки в массив для кластеризации
        double[][] data = points.stream()
                .map(p -> new double[]{p.getLatitude(), p.getLongitude()})
                .toArray(double[][]::new);

        // Применяем алгоритм KMeans
        KMeans kmeans = KMeans.fit(data, k);

        // Создаем список кластеров
        List<List<Point>> clusters = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            clusters.add(new ArrayList<>());
        }

        // Распределяем точки по кластерам
        for (int i = 0; i < points.size(); i++) {
            clusters.get(kmeans.y[i]).add(points.get(i));
        }

        return clusters;
    }
}

