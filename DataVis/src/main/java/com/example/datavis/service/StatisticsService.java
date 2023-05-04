package com.example.datavis.service;

import com.example.datavis.entity.Statistics;

import java.util.List;

public interface StatisticsService {

    public List<Statistics> findAll();

    public Statistics findById(int theId);

    public List<Integer> getYearsList(List<Statistics> stats);

    public List<Statistics> filterByCountry(List<Statistics> stats,List<String> code,String indicator,int startYear,int endYear);
}
