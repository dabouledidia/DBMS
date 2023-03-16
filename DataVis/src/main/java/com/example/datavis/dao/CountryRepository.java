package com.example.datavis.dao;
import com.example.datavis.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

    public Country findById(int Id);

}
