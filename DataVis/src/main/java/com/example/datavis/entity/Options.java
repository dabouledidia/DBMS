package com.example.datavis.entity;

import java.util.List;

public class Options {
    private List<String> country;
    private String indicator;
    private String chart_Name;
    private int startYear;
    private int endYear;

    public Options(List<String> country, String indicator, String chart_Name, int startYear, int endYear) {
        this.country = country;
        this.indicator = indicator;
        this.chart_Name = chart_Name;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Options() {
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
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
}
