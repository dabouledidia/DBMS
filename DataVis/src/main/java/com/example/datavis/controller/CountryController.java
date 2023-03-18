package com.example.datavis.controller;
import com.example.datavis.dao.StatisticsRepository;
import com.example.datavis.entity.Country;
import com.example.datavis.entity.Options;
import com.example.datavis.entity.Statistics;
import com.example.datavis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @GetMapping("/query")
    public String getQuery(Model model) {
        Options options = new Options();
        String[] charts = {"barchart", "timeline", "scatter"};
        // This returns a JSON or XML with the users
        model.addAttribute("countries",countryService.findAll());

        model.addAttribute("indicators",indicatorService.findAll());
        model.addAttribute("options",options);
        model.addAttribute("charts", charts);

        return "query";
    }

    @PostMapping("/query")
    public String submitForm(@ModelAttribute("option") Options option,Model model) {
        System.out.println(option.getStartYear());

        List<Statistics> results = statisticsRepository.findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan
                (option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
        System.out.println(results.get(0).getValue());
        System.out.println(results.size());
        model.addAttribute("results",results);
        return "charts";
    }
}
