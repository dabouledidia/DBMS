package com.example.datavis.entity;

public class Statistics {
    private Long id;
    private String FIPS;
    private int Year;
    private String Indicator;
    private double Value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFIPS() {
        return FIPS;
    }

    public void setFIPS(String FIPS) {
        this.FIPS = FIPS;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getIndicator() {
        return Indicator;
    }

    public void setIndicator(String indicator) {
        Indicator = indicator;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public Statistics(Long id, String FIPS, int year, String indicator, double value) {
        this.id = id;
        this.FIPS = FIPS;
        Year = year;
        Indicator = indicator;
        Value = value;
    }

    public Statistics(String FIPS, int year, String indicator, double value) {
        this.FIPS = FIPS;
        Year = year;
        Indicator = indicator;
        Value = value;
    }
}
