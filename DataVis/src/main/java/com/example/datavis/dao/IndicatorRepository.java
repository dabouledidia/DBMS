package com.example.datavis.dao;
import com.example.datavis.entity.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository  extends JpaRepository<Indicator,Integer> {

    public Indicator findById(int Id);

}
