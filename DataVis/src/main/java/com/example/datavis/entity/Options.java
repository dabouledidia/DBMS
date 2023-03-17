package com.example.datavis.entity;

public class Options {
    private String country;
    private String indicator;
    private String chart_Name;

    public Options() {
    }

    public Options(String country, String indicator, String chart) {
        this.country = country;
        this.indicator = indicator;
        this.chart_Name = chart;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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

}
