package com.example.demo.repository;

import com.example.demo.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City,Long> {
    Page<City> findAll(Pageable pageable);
    Optional<City> findByName(String name);
}
