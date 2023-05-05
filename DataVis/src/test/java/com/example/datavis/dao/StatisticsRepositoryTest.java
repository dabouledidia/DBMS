package com.example.datavis.dao;

import com.example.datavis.entity.Statistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class StatisticsRepositoryTest {

    @Autowired
    StatisticsRepository statisticsRepository;

    @Test
    void testFindByCodeInAndIndicatorInAndYearGreaterThanAndYearLessThan()
    {
        List<String> countries = new ArrayList<>();
        countries.add("GM");

        List<String> indicators = new ArrayList<>();
        indicators.add("crude_birth_rate");

        List<Statistics> stats = statisticsRepository.findByCodeInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(countries,indicators,1991,1991);

        List<Statistics> expected_stats = new ArrayList<>();

        expected_stats.add(new Statistics("GM",1991,"crude_birth_rate",10.38));

        Assertions.assertEquals(stats.get(0).getCode(),expected_stats.get(0).getCode());
        Assertions.assertEquals(stats.get(0).getYear(),expected_stats.get(0).getYear());
        Assertions.assertEquals(stats.get(0).getIndicator(),expected_stats.get(0).getIndicator());
        Assertions.assertEquals(stats.get(0).getValue(),expected_stats.get(0).getValue());
    }
}
