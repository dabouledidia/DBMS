package com.example.datavis.dao;
import com.example.datavis.entity.Country;
import com.example.datavis.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,Integer> {

    public Statistics findById(int Id);

    public List<Statistics> findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan(List<String> countryCode,String indicatorCode,int startYear,int endYear);

    String aggregateByFive = "SELECT id,Country,avg(Value) as Value,Year,Indicator FROM dbms.statistics group by Indicator,Country, YEAR DIV 5";

    @Query(value = aggregateByFive, nativeQuery = true)
    public List<Statistics> findAggregatedByFive();

    String aggregateByTen = "SELECT id,Country,avg(Value) as Value,Year,Indicator FROM dbms.statistics group by Indicator,Country, YEAR DIV 10";

    @Query(value = aggregateByTen, nativeQuery = true)
    public List<Statistics> findAggregatedByTen();
}
