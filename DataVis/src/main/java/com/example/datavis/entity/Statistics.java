package com.example.datavis.entity;
import jakarta.persistence.*;

import java.beans.ConstructorProperties;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Country")
    private String code;

    @Column(name = "Year")
    private int year;

    @Column(name = "Indicator")
    private String indicator;

    @Column(name = "Value")
    private double value;

    public Statistics() {
    }

    public Statistics(String code, int year, String indicator, double value) {
        this.code = code;
        this.year = year;
        this.indicator = indicator;
        this.value = value;
    }

    public Statistics(int id, String code, int year, String indicator, double value) {
        this.id = id;
        this.code = code;
        this.year = year;
        this.indicator = indicator;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
