package com.example.datavis.controller;
import com.example.datavis.dao.StatisticsRepository;
import com.example.datavis.entity.Options;
import com.example.datavis.entity.Statistics;
import com.example.datavis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import org.json.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

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

//    @PostMapping("/query")
//    public String submitForm(@ModelAttribute("option") Options option, Statistics statistics, Model model) {
//        System.out.println(option.getStartYear());
//        System.out.println(option.getEndYear());
//        List<Statistics> results = statisticsRepository.findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan
//                (option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
//        System.out.println(results.size());
//        model.addAttribute("results",results);
//        return "charts";
//    }


    @PostMapping("/query")
    public ModelAndView chartForm(@ModelAttribute("option") Options option, Statistics statistics, ModelMap model) {
        int start = option.getStartYear();
        List<Statistics> stats = statisticsRepository.findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan(option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
        System.out.println(stats.size());
        model.addAttribute("stats",stats);
        Map<Integer, Double> valuesAndYear= new HashMap<Integer, Double>();
        for (int i = 0; i < stats.size(); i++){
            valuesAndYear.put((i+start), stats.get(i).getValue());
        }

        List<Integer> sortYear = new ArrayList<Integer>(valuesAndYear.keySet());
        Collections.sort(sortYear);
        String s = "key, value\n";
        for (Integer i : sortYear){
            Double v = valuesAndYear.get(i);
            s = s + i + ", " + v + "\n";
        }

        String pageTitle = "Bar Chart";

        JSONArray results = CDL.toJSONArray(s);
        model.addAttribute("dataMap", results);
        model.addAttribute("title", pageTitle);
        return new ModelAndView("barchart", model);
    }
}
