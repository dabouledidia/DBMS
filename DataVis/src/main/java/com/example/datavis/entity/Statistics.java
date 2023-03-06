package com.example.datavis.entity;
import jakarta.persistence.*;

import java.beans.ConstructorProperties;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "Country")
    private String FIPS;

    @Column(name = "Year")
    private int Year;

    @Column(name = "Indicator")
    private String Indicator;

    @Column(name = "Value")
    private double Value;

    public Statistics() {
    }

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
