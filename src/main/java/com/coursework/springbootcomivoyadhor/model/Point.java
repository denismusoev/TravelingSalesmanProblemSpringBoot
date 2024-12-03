package com.coursework.springbootcomivoyadhor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Point {
    private String name; // Название точки (например, город)
    private double latitude; // Широта
    private double longitude; // Долгота
}

