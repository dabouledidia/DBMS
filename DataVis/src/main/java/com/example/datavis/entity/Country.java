package com.example.datavis.entity;

import jakarta.persistence.*;
//import javax.persistence.*;

@Entity
@Table(name = "countries")

public class Country {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "FIPS")
    private String FIPS;

    @Column(name = "Display_Name")
    private String Name;

    @Column(name = "Continent")
    private String Continent;

    @Column(name = "CurrencyName")
    private String Currency;

    @Column(name = "Area_Sqkm")
    private int Area;

    @Column(name = "Population")
    private int Population;

    public Integer getArea() {
        return Area;
    }

    public Country() {
    }

    public Country(String FIPS, String name, String continent, String currency, int area, int population) {
        this.FIPS = FIPS;
        Name = name;
        Continent = continent;
        Currency = currency;
        Area = area;
        Population = population;
    }

    public Country(Long id, String FIPS, String name, String continent, String currency, int area, int population) {
        this.id = id;
        this.FIPS = FIPS;
        Name = name;
        Continent = continent;
        Currency = currency;
        Area = area;
        Population = population;
    }

    public void setArea(Integer area) {
        Area = area;
    }

    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer population) {
        Population = population;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }



    @Override
    public String toString() {
        return "Country{" +
                "FIPS='" + FIPS + '\'' +
                ", Name='" + Name + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Currency='" + Currency + '\'' +
                ", Area=" + Area +
                ", Population=" + Population +
                '}';
    }
}
