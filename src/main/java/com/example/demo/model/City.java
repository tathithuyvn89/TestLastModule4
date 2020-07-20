package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Entity
@Data
  public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double area;
   @Min(100000)
    private BigInteger population;
    private BigInteger gdp;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
   private Country country;
}