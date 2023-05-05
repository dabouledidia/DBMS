package com.example.datavis.service;

import com.example.datavis.dao.StatisticsRepository;
import com.example.datavis.entity.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class StatisticsServiceImplTest {

    @Autowired
    StatisticsServiceImpl statisticsService;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Test
    void testFilterByCountry()
    {
        List<String> countries = new ArrayList<>();
        countries.add("GM");

        List<String> indicators = new ArrayList<>();
        indicators.add("crude_birth_rate");

        List<Statistics> stats = statisticsRepository.findAggregatedByFive();

        List<Statistics> filtered_stats = statisticsService.filterByCountry(stats,countries,indicators,1990,2005);

        List<Statistics> expected_stats = new ArrayList<>();

        expected_stats.add(new Statistics("GM",1994,"crude_birth_rate",9.9275));
        expected_stats.add(new Statistics("GM",1999,"crude_birth_rate",9.59));
        expected_stats.add(new Statistics("GM",2004,"crude_birth_rate",8.818000000000001));

        for(int i = 0; i<3;i++)
        {
            Assertions.assertEquals(filtered_stats.get(i).getCode(),expected_stats.get(i).getCode());
            Assertions.assertEquals(filtered_stats.get(i).getYear(),expected_stats.get(i).getYear());
            Assertions.assertEquals(filtered_stats.get(i).getIndicator(),expected_stats.get(i).getIndicator());
            Assertions.assertEquals(filtered_stats.get(i).getValue(),expected_stats.get(i).getValue());        }
    }

}
