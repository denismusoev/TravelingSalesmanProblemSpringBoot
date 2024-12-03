package com.coursework.springbootcomivoyadhor.service;

import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GeneticAlgorithmService {

    private static final int POPULATION_SIZE = 250;
    private static final int GENERATIONS = 1000;
    private int CURRENT_GENERATION = 0;
    private static final double MUTATION_RATE = 0.02;

    private final DistanceCalculator distanceCalculator;

    public GeneticAlgorithmService(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public List<Point> findOptimalRoute(List<Point> points) {
        List<List<Point>> population = initializePopulation(points);
        for (int i = 0; i < GENERATIONS; i++) {
            population = evolvePopulation(population);
            CURRENT_GENERATION = i;
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
        List<Point> best = null;
        double bestFitness = Double.MAX_VALUE;
        for (int i = 0; i < 5; i++) { // Турнир из 5 случайных маршрутов
            List<Point> candidate = population.get(new Random().nextInt(population.size()));
            double fitness = distanceCalculator.getRouteDistance(candidate);
            if (fitness < bestFitness) {
                best = candidate;
                bestFitness = fitness;
            }
        }
        return best;
    }


    private List<Point> crossover(List<Point> parent1, List<Point> parent2) {
        int start = new Random().nextInt(parent1.size());
        int end = start + new Random().nextInt(parent1.size() - start);
        List<Point> child = new ArrayList<>(parent1.subList(start, end)); // Берём подмножество из parent1

        for (Point point : parent2) { // Добавляем точки из parent2, которых нет в подмножестве
            if (!child.contains(point)) {
                child.add(point);
            }
        }
        return child;
    }



    private void mutate(List<Point> route) {
        double dynamicMutationRate = MUTATION_RATE / (1 + CURRENT_GENERATION / 100.0); // Плавное снижение мутаций
        for (int i = 0; i < route.size(); i++) {
            if (Math.random() < dynamicMutationRate) {
                int j = new Random().nextInt(route.size());
                Collections.swap(route, i, j);
            }
        }
    }


    private List<Point> getFittest(List<List<Point>> population) {
        return population.stream()
                .min((route1, route2) -> Double.compare(distanceCalculator.getRouteDistance(route1), distanceCalculator.getRouteDistance(route2)))
                .orElseThrow();
    }
}

