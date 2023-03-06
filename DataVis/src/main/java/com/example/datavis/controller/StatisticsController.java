package com.example.datavis.controller;

import com.example.datavis.dao.StatisticsRepository;
import com.example.datavis.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/stats")
public class StatisticsController {
    @Autowired
    private StatisticsRepository statisticsRepository;
    @GetMapping
    public @ResponseBody
    Iterable<Statistics> getAllUsers() {
        // This returns a JSON or XML with the users
        return statisticsRepository.findAll();
    }
}
