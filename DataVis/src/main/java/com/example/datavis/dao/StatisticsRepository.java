package com.example.datavis.dao;
import com.example.datavis.entity.Country;
import com.example.datavis.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,Integer> {

    public Statistics findById(int Id);

    public List<Statistics> findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan(List<String> countryCode,String indicatorCode,int startYear,int endYear);

}
