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

    @PostMapping("/query")
    public ModelAndView chartForm(@ModelAttribute("option") Options option, Statistics statistics, ModelMap model) {

        List<Statistics> stats = statisticsRepository.findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan(option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
        model.addAttribute("stats",stats);
        List<String> indicators = new ArrayList<String>();

        List<Integer> years = new ArrayList<>();
        for (Statistics i : stats){
            years.add(i.getYear());
        }
        Collections.sort(years);

        int max = Collections.max(years);


        // Add to the indicators list all the country codes the user selected.
        for (int i = 0; i < stats.size(); i++){
            if (!indicators.contains(stats.get(i).getCode()) ){indicators.add(stats.get(i).getCode());}
        }


        // we will have a Map in the following format [indicator : { year(s) : value(s) }] called categories
        Map<Integer, Double> valuesAndYear = new HashMap<Integer, Double>();
        Map <String, Map<Integer, Double>> categories = new HashMap<>();


        for ( int j = 0; j < indicators.size(); j++){
            for (int i = 0; i < stats.size(); i++){
                if(stats.get(i).getCode().equals(indicators.get(j))){
                    valuesAndYear.put(stats.get(i).getYear(), stats.get(i).getValue());
                }
            }
            TreeMap<Integer, Double> sorted = new TreeMap<>();
            sorted.putAll(valuesAndYear);
            categories.put(indicators.get(j), sorted);
        }

        List<String> sortCat = new ArrayList<String>(categories.keySet());
        Collections.sort(sortCat);
        int sortcatMax = sortCat.size() - 1;


        // Creates JSON object like string to pass to d3

        String jsonString = "[\r\n";
        for (String i : sortCat) {
            jsonString += "{\r\n";
            Map<Integer, Double> vals = categories.get(i);
            jsonString += "        \"categorie\": \"" + i + "\", \r\n";
            jsonString += "         \"values\": [\r\n";
            System.out.println(i);
            for (Integer yearValue : vals.keySet()) {


                if (yearValue != max){
                    jsonString += "       {\r\n"
                        + "            \"value\": " + "\"" + categories.get(i).get(yearValue) + "\","
                        + "\"rate\": " + "\"" + yearValue + "\"},";}
                else{
                    jsonString += "       {\r\n"
                            + "            \"value\": " + "\"" + categories.get(i).get(yearValue) + "\","
                            + "\"rate\": " + "\"" + yearValue + "\"}";
                }
            System.out.println(categories.get(i).get(yearValue) + " " + yearValue);
            }




            if( i != sortCat.get(sortcatMax)) {jsonString += "]\r\n},\r\n";}

            else{jsonString += "]\r\n}\r\n]";}


        }



        model.addAttribute("dataMap", jsonString);
        return new ModelAndView((option.getChart()), model);
    }
}
