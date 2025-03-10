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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MainController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsServiceImpl statisticsService;
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/query")
    public String getQuery(Model model) {
        Options options = new Options();
        String[] charts = {"barchart", "timeline", "scatter"};
        String[] yearType = {"Aggregate by 5 years", "Aggregate by 10 years", "Aggregate by year"};
        // This returns a JSON or XML with the users
        model.addAttribute("countries",countryService.findAll());
        model.addAttribute("indicators",indicatorService.findAll());
        model.addAttribute("options",options);
        model.addAttribute("charts", charts);
        model.addAttribute("yearType", yearType);

        return "query";
    }

    @PostMapping("/query")
    public ModelAndView chartForm(@ModelAttribute("option") Options option, Statistics statistics, ModelMap model) {



        List<Statistics> stats = new ArrayList<>();
        int agg = 0;
        if(option.getYearType().equals("Aggregate by 5 years"))
        {
            stats = statisticsService.filterByCountry(statisticsRepository.findAggregatedByFive(),option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
            agg = 5;
        }
        if(option.getYearType().equals("Aggregate by 10 years"))
        {
            stats = statisticsService.filterByCountry(statisticsRepository.findAggregatedByTen(),option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
            agg = 10;
        }
        if(option.getYearType().equals("Aggregate by year"))
        {
            stats = statisticsRepository.findByCodeInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(option.getCountry(), option.getIndicator(), option.getStartYear(), option.getEndYear());

        }

        model.addAttribute("stats",stats);

        if(stats.isEmpty())
        {
            return new ModelAndView("error_stats",model);
        }

        List<String> indicators = new ArrayList<String>();
        List<String> indicatorsMetrics = new ArrayList<String>();

        List<Integer> years = new ArrayList<>();
        for (Statistics i : stats){
            years.add(i.getYear());
        }
        Collections.sort(years);



        // Add to the indicators list all the country codes the user selected.
        for (int i = 0; i < stats.size(); i++){
            if (!indicators.contains(stats.get(i).getCode()) ){indicators.add(stats.get(i).getCode());}
            if (!indicatorsMetrics.contains(stats.get(i).getIndicator()) ){indicatorsMetrics.add(stats.get(i).getIndicator());}
        }

        System.out.println(indicatorsMetrics);


        // we will have a Map in the following format [indicator : { year(s) : value(s) }] called categories
        Map<Integer, Double> valuesAndYear = new HashMap<Integer, Double>();
        Map <String, Map<Integer, Double>> categories = new HashMap<>();

        System.out.println(indicators);

        for ( int j = 0; j < indicators.size(); j++){
            for ( int k = 0; k < indicatorsMetrics.size(); k++) {
                for (int i = 0; i < stats.size(); i++) {
                    if ((stats.get(i).getCode().equals(indicators.get(j))) && stats.get(i).getIndicator().equals(indicatorsMetrics.get(k))) {
                        valuesAndYear.put(stats.get(i).getYear(), stats.get(i).getValue());

                    }
                }
                TreeMap<Integer, Double> sorted = new TreeMap<>();
                sorted.putAll(valuesAndYear);
                valuesAndYear.clear();
                categories.put(indicators.get(j) + " (" + indicatorsMetrics.get(k) + ")", sorted);
            }

        }



        List<String> sortCat = new ArrayList<String>(categories.keySet());
        Collections.sort(sortCat);
        int sortcatMax = sortCat.size() - 1;


        // Creates JSON object like string to pass to d3

        String jsonString = "[\r\n";
        for (String i : sortCat) {
            jsonString += "{\r\n";
            Map<Integer, Double> vals = categories.get(i);
            int max = 0;
            for (Integer yearValue : vals.keySet()) {
                max = yearValue;
            }
            jsonString += "        \"categorie\": \"" + i + "\", \r\n";
            jsonString += "         \"values\": [\r\n";
            if (agg != 0) {
                for (Integer yearValue : vals.keySet()) {


                    if (yearValue != max) {
                        jsonString += "       {\r\n"
                                + "            \"value\": " + "\"" + categories.get(i).get(yearValue) + "\","
                                + "\"rate\": " + "\"" + (yearValue - agg + 1) + "\"},";
                    } else {
                        jsonString += "       {\r\n"
                                + "            \"value\": " + "\"" + categories.get(i).get(yearValue) + "\","
                                + "\"rate\": " + "\"" + (yearValue) + "\"}";
                    }
                    System.out.println(categories.get(i).get(yearValue) + " " + yearValue);

                }

                if (i != sortCat.get(sortcatMax)) {
                    jsonString += "]\r\n},\r\n";
                } else {
                    jsonString += "]\r\n}\r\n]";
                }


            } else {
                for (Integer yearValue : vals.keySet()) {

                    if (yearValue != max) {
                        jsonString += "       {\r\n"
                                + "            \"value\": " + "\"" + categories.get(i).get(yearValue) + "\","
                                + "\"rate\": " + "\"" + yearValue + "\"},";
                    } else {
                        jsonString += "       {\r\n"
                                + "            \"value\": " + "\"" + categories.get(i).get(yearValue) + "\","
                                + "\"rate\": " + "\"" + (yearValue) + "\"}";
                    }
                    System.out.println(categories.get(i).get(yearValue) + " " + yearValue);

                }
                if (i != sortCat.get(sortcatMax)) {
                    jsonString += "]\r\n},\r\n";
                } else {
                    jsonString += "]\r\n}\r\n]";
                }

            }
        }
        System.out.println(jsonString);

        model.addAttribute("dataMap", jsonString);
        return new ModelAndView((option.getChart()), model);
    }
}
