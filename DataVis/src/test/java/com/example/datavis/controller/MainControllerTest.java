package com.example.datavis.controller;

import com.example.datavis.dao.StatisticsRepository;
import com.example.datavis.entity.Options;
import com.example.datavis.entity.Statistics;
import com.example.datavis.service.CountryService;
import com.example.datavis.service.IndicatorService;
import com.example.datavis.service.StatisticsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MainControllerTest {

    @Mock
    private CountryService countryService;

    @Mock
    private IndicatorService indicatorService;

    @Mock
    private StatisticsRepository statisticsRepository;

    @Mock
    private StatisticsService statisticsService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHome() {
        String result = mainController.home();
        String expectedResult = "home";

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testGetQuery() {
        Model model = mock(Model.class);

        when(countryService.findAll()).thenReturn(new ArrayList<>());
        when(indicatorService.findAll()).thenReturn(new ArrayList<>());
        when(model.addAttribute(anyString(), any())).thenReturn(model);

        String result = mainController.getQuery(model);
        String expectedResult = "query";

        Assertions.assertEquals(expectedResult, result);
    }


    @Test
    public void testChartForm() {
        List<String> country = Arrays.asList("China");
        List<String> indicator = Arrays.asList("CHI");
        String chartName = "barchart";
        int startYear = 1950;
        int endYear = 2000;
        String yearType = "";

        Options option = new Options(country, indicator, chartName, startYear, endYear, yearType);
        Statistics statistics = new Statistics();
        ModelMap model = mock(ModelMap.class);

        List<Statistics> stats = new ArrayList<>();


        when(statisticsRepository.findByCodeInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
            anyList(), anyList(), anyInt(), anyInt())).thenReturn(stats);


        ModelAndView modelAndView = mainController.chartForm(option, statistics, model);


        Assertions.assertEquals("error_stats", modelAndView.getViewName());

    }

    @Test
    public void testChartFormNoError() {
        List<String> country = Arrays.asList("China");
        List<String> indicator = Arrays.asList("CHI");
        String chartName = "barchart";
        int startYear = 1950;
        int endYear = 2000;
        String yearType = "Aggregate by year";

        Options option = new Options(country, indicator, chartName, startYear, endYear, yearType);
        Statistics statistics = new Statistics("China", 1990, "CHI", 100.0);
        ModelMap model = mock(ModelMap.class);

        List<Statistics> stats = Arrays.asList(statistics);

        when(statisticsRepository.findByCodeInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
                anyList(), anyList(), anyInt(), anyInt())).thenReturn(stats);

        ModelAndView modelAndView = mainController.chartForm(option, statistics, model);

        Assertions.assertNotEquals("error_stats", modelAndView.getViewName());
    }




}
