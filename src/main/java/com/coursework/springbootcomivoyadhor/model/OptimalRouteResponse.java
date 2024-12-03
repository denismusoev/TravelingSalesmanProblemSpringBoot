package com.coursework.springbootcomivoyadhor.model;

import com.coursework.springbootcomivoyadhor.model.Point;
import java.util.List;

public class OptimalRouteResponse {
    private List<Point> route;
    private double totalDistance;

    public OptimalRouteResponse(List<Point> route, double totalDistance) {
        this.route = route;
        this.totalDistance = totalDistance;
    }

    public List<Point> getRoute() {
        return route;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
}

