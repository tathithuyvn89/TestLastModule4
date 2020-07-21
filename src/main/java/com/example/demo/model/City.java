package com.example.demo.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Entity
@Data
  public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private double area;
   @Min(value = 100000,message = "Phai lon hon 100000")
    private BigInteger population;
    private BigInteger gdp;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
   private Country country;
}