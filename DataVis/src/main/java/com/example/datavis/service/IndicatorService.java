package com.example.datavis.service;

import com.example.datavis.entity.Indicator;

import java.util.List;

public interface IndicatorService {

    public List<Indicator> findAll();

    public Indicator findById(int theId);
}
