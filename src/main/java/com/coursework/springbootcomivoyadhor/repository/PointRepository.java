package com.coursework.springbootcomivoyadhor.repository;

import com.coursework.springbootcomivoyadhor.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
}

