package com.example.datavis.entity;

import java.util.List;

public class Options {
    private List<String> country;
    private List<String> indicator;
    private String chart_Name;
    private int startYear;
    private int endYear;

    private String yearType;

    public Options(List<String> country, List<String> indicator, String chart_Name, int startYear, int endYear, String yearType) {
        this.country = country;
        this.indicator = indicator;
        this.chart_Name = chart_Name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.yearType = yearType;
    }

    public Options() {
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getIndicator() {
        return indicator;
    }

    public void setIndicator(List<String> indicator) {
        this.indicator = indicator;
    }

    public String getChart() {
        return chart_Name;
    }

    public void setChart(String chart) {
        this.chart_Name = chart;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getYearType() {
        return yearType;
    }

    public void setYearType(String yearType) {
        this.yearType = yearType;
    }
}
