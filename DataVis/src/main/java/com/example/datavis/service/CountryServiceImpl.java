package com.example.datavis.service;

import com.example.datavis.dao.CountryRepository;
import com.example.datavis.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public CountryServiceImpl() {
        super();
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(int theId) {
        Country res = countryRepository.findById(theId);
        if(res != null)
        {
            return res;
        }
        else
        {
            throw new RuntimeException("Did not find country with id: "+theId);
        }
    }
}

//    @Override
//    public Country findByCode(String theCode) {
//        Country result = countryRepository.findByCode(theCode);
//
//        if (result != null ) {
//            return result;
//        }
//        else {
//            throw new RuntimeException("Did not find country code - " + theCode);
//        }    }

//    @Override
//    public List<Country> getCountriesByStrings(List<String> countries) {
//        List<Country> countriesList = new ArrayList<>();
//
//        for(String country : countries) {
//            countriesList.add(this.findByCode(country));
//        }
//        return countriesList;    }
//}
