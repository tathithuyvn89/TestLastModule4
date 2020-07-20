package com.example.demo.service;

import com.example.demo.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityService {
    Page<City> findAll(Pageable pageable);
    Optional<City> findCityById(Long id);
    Optional<City> findByName(String name);
    void saveCity(City city);
    void moveCity(Long id);
}
