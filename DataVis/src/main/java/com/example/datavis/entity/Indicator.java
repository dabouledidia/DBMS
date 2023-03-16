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
    private String Name;

    public Indicator() {
    }

    public Indicator(String name) {
        Name = name;
    }

    public Indicator(int id, String name) {
        this.id = id;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Indicator{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
