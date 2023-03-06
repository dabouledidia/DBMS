package com.example.datavis;

import com.example.datavis.entity.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class DataVisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataVisApplication.class, args);
    }

}
