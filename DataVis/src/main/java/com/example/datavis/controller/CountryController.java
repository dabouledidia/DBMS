package com.example.datavis.controller;

import com.example.datavis.dao.CountryRepository;
import com.example.datavis.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(path = "/countries")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;
    @GetMapping
    public @ResponseBody
    Iterable<Country> getAllUsers() {
        // This returns a JSON or XML with the users
        return countryRepository.findAll();
    }
}
