package com.example.datavis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Code")
    private String code;

    @Column(name = "Display_Name")
    private String name;

    @Column(name = "Continent")
    private String continent;

    @Column(name = "Currency_Name")
    private String currency;

    @Column(name = "Area_Sqkm")
    private int area;

    @Column(name = "Population")
    private int population;


    public Country() {
    }

    public Country(String code, String name, String continent, String currency, int area, int population) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.currency = currency;
        this.area = area;
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", currency='" + currency + '\'' +
                ", area=" + area +
                ", population=" + population +
                '}';
    }
}
