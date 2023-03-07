package com.example.datavis.service;

import com.example.datavis.entity.Country;

import java.util.List;

public interface CountryService {

    public List<Country> findAll();

    public Country findById(int theId);

    public Country findByCode(String theCode);

    public List<Country> getCountriesByStrings(List<String> countries);
}
