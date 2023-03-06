package com.example.datavis.dao;
import com.example.datavis.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics,Long> {
}
