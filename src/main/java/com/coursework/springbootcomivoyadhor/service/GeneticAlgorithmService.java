package com.coursework.springbootcomivoyadhor.service;

import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GeneticAlgorithmService {

    private static final int POPULATION_SIZE = 100;
    private static final int GENERATIONS = 500;
    private static final double MUTATION_RATE = 0.02;

    public List<Point> findOptimalRoute(List<Point> points) {
        List<List<Point>> population = initializePopulation(points);
        for (int i = 0; i < GENERATIONS; i++) {
            population = evolvePopulation(population);
        }
        return getFittest(population);
    }

    private List<List<Point>> initializePopulation(List<Point> points) {
        List<List<Point>> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<Point> newRoute = new ArrayList<>(points);
            Collections.shuffle(newRoute);
            population.add(newRoute);
        }
        return population;
    }

    private List<List<Point>> evolvePopulation(List<List<Point>> population) {
        List<List<Point>> newPopulation = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<Point> parent1 = selectParent(population);
            List<Point> parent2 = selectParent(population);
            List<Point> child = crossover(parent1, parent2);
            mutate(child);
            newPopulation.add(child);
        }
        return newPopulation;
    }

    private List<Point> selectParent(List<List<Point>> population) {
        return population.get(new Random().nextInt(population.size()));
    }

    private List<Point> crossover(List<Point> parent1, List<Point> parent2) {
        int start = new Random().nextInt(parent1.size());
        int end = start + new Random().nextInt(parent1.size() - start);
        List<Point> child = new ArrayList<>(parent1.subList(start, end));

        for (Point point : parent2) {
            if (!child.contains(point)) {
                child.add(point);
            }
        }
        return child;
    }

    private void mutate(List<Point> route) {
        for (int i = 0; i < route.size(); i++) {
            if (Math.random() < MUTATION_RATE) {
                int j = new Random().nextInt(route.size());
                Collections.swap(route, i, j);
            }
        }
    }

    private List<Point> getFittest(List<List<Point>> population) {
        return population.stream()
                .min((route1, route2) -> Double.compare(getRouteDistance(route1), getRouteDistance(route2)))
                .orElseThrow();
    }

    private double getRouteDistance(List<Point> route) {
        double totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += distance(route.get(i), route.get(i + 1));
        }
        totalDistance += distance(route.get(route.size() - 1), route.get(0)); // Замыкаем маршрут
        return totalDistance;
    }

    private double distance(Point p1, Point p2) {
        double latDiff = p1.getLatitude() - p2.getLatitude();
        double lonDiff = p1.getLongitude() - p2.getLongitude();
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }
}

