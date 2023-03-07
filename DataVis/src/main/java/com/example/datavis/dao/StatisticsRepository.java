package com.example.datavis.dao;
import com.example.datavis.entity.Country;
import com.example.datavis.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics,Integer> {

    public Statistics findById(int Id);

    public Country findByFIPS(String Code);


}
