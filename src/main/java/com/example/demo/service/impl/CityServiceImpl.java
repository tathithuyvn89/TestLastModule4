package com.example.demo.service.impl;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
  public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public Optional<City> findCityById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);

    }

    @Override
    public void moveCity(Long id) {
        cityRepository.deleteById(id);

    }
}