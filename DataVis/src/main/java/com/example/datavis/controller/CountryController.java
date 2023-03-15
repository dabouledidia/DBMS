package com.example.datavis.controller;

import com.example.datavis.dao.CountryRepository;
import com.example.datavis.dao.StatisticsRepository;
import com.example.datavis.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @GetMapping("/")
    public String showData(Model model) {
        // This returns a JSON or XML with the users
        model.addAttribute("countries",countryRepository.findAll());

        model.addAttribute("stats",statisticsRepository.findAll());

        return "home";
    }
}
