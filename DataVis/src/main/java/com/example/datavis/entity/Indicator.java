package com.example.datavis.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "indicators")
public class Indicator {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Clean_Name")
    private String clean_name;

    public Indicator() {
    }

    public Indicator(String name, String clean_name) {
        this.name = name;
        this.clean_name = clean_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClean_name() {
        return clean_name;
    }

    public void setClean_name(String clean_name) {
        this.clean_name = clean_name;
    }

    @Override
    public String toString() {
        return "Indicator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clean_name='" + clean_name + '\'' +
                '}';
    }
}
