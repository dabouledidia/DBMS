package com.example.datavis.controller;
import com.example.datavis.service.CountryServiceImpl;
import com.example.datavis.service.IndicatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CountryController {
    @Autowired
    private CountryServiceImpl countryService;

    @Autowired
    private IndicatorServiceImpl indicatorService;

    @GetMapping("/")
    public String showData(Model model) {
        // This returns a JSON or XML with the users
        model.addAttribute("countries",countryService.findAll());

        model.addAttribute("indicators",indicatorService.findAll());

        return "home";
    }
}
