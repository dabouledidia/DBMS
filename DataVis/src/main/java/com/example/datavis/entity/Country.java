package com.example.datavis.entity;

public class Country {
    private Long id;
    private String FIPS;
    private String Name;
    private String Continent;
    private String Currency;
    private int Area;
    private int Population;

    public Integer getArea() {
        return Area;
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
