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
//    public ModelAndView chartForm(@ModelAttribute("option") Options option, Statistics statistics, ModelMap model) {
//        int start = option.getStartYear();
//        List<Statistics> stats = statisticsRepository.findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan(option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
//        System.out.println(stats.size());
//        model.addAttribute("stats",stats);
//        Map<Integer, Double> valuesAndYear= new HashMap<Integer, Double>();
//        for (int i = 0; i < stats.size(); i++){
//            valuesAndYear.put((i+start), stats.get(i).getValue());
//        }
//
//        List<Integer> sortYear = new ArrayList<Integer>(valuesAndYear.keySet());
//        Collections.sort(sortYear);
//        String s = "key, value\n";
//        for (Integer i : sortYear){
//            Double v = valuesAndYear.get(i);
//            s = s + i + ", " + v + "\n";
//        }
//
//        String pageTitle = "Bar Chart";
//
//        JSONArray results = CDL.toJSONArray(s);
//        model.addAttribute("dataMap", results);
//        model.addAttribute("title", pageTitle);
//        return new ModelAndView("scatter", model);
//    }

    @PostMapping("/query")
    public ModelAndView chartForm(@ModelAttribute("option") Options option, Statistics statistics, ModelMap model) {

        int start = option.getStartYear();

        List<Statistics> stats = statisticsRepository.findByCodeInAndIndicatorAndYearGreaterThanAndYearLessThan(option.getCountry(),option.getIndicator(), option.getStartYear(),option.getEndYear());
        model.addAttribute("stats",stats);
        List<String> categs = new ArrayList<String>();

        for (int i = 0; i < stats.size(); i++){
            if (!categs.contains(stats.get(i).getCode()) ){categs.add(stats.get(i).getCode());}
        }

        Map <String, Map<Integer, Double>> categories = new HashMap<>();
        Map<Integer, Double> valuesAndYear= new HashMap<Integer, Double>();

        for ( int j = 0; j < categs.size(); j++){
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
            categories.put(categs.get(j), valuesAndYear);

        }

        List<String> sortCat = new ArrayList<String>(categories.keySet());
        Collections.sort(sortCat);
        String s = "[\r\n";
        for (String i : sortCat) {
            s += "{\r\n";
            Map<Integer, Double> vals = categories.get(i);
            s += "        \"categorie\": \"" + i + "\", \r\n";
            s += "         \"values\": [\r\n";
            for (Integer year : vals.keySet()) {
                if (year != 1951){
                s += "       {\r\n"
                        + "            \"year\": " + "\"" + vals.get(year) + "\","
                        + "\"val\": " + "\"" + year + "\"},";}
                else{
                    s += "       {\r\n"
                            + "            \"year\": " + "\"" + vals.get(year) + "\","
                            + "\"val\": " + "\"" + year + "\"}";
                }
            }
            if( i != sortCat.get(1)) {s += "]\r\n},\r\n";}
            else{s += "]\r\n}\r\n]";}
        }


        System.out.println(s);

        model.addAttribute("dataMap", s);
        return new ModelAndView("barchart", model);
    }
}
