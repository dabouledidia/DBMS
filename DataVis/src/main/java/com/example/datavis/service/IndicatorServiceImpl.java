package com.example.datavis.service;
import com.example.datavis.dao.IndicatorRepository;
import com.example.datavis.entity.Indicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorServiceImpl implements IndicatorService {
    @Autowired
    private IndicatorRepository indicatorRepository;

    public IndicatorServiceImpl() {
        super();
    }

    @Override
    public List<Indicator> findAll() {
        return indicatorRepository.findAll();
    }

    @Override
    public Indicator findById(int theId) {
        Indicator res = indicatorRepository.findById(theId);
        if(res != null)
        {
            return res;
        }
        else
        {
            throw new RuntimeException("Did not find indicator with id: "+theId);
        }
    }
}


