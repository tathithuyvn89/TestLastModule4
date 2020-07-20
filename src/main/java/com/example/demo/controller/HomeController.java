package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.CityService;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Controller
  public class HomeController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;
    @ModelAttribute("countries")
    public Iterable<Country> listCountries(){
      return countryService.findAllCountry();
    }
    @GetMapping("/createNewCity")
  public ModelAndView showCreateCityPage(){
      ModelAndView modelAndView = new ModelAndView("createnewcity");
      modelAndView.addObject("newCity",new City());
      return modelAndView;
    }
  @PostMapping("/createNewCity")
  public ModelAndView saveNewCity(@ModelAttribute City newCity, BindingResult bindingResult){
      ModelAndView modelAndView = new ModelAndView("createnewcity");
        if(bindingResult.hasFieldErrors()){
            return modelAndView;
        }
     String cityName= newCity.getName();
     Optional<City> cityInRepo = cityService.findByName(cityName);
     if (cityInRepo.isPresent()){
       modelAndView.addObject("message","City is ready in system");
       modelAndView.addObject("newCity",newCity);
     } else {
         cityService.saveCity(newCity);
       modelAndView.addObject("message","City create success");
       modelAndView.addObject("newCity",newCity);
     }
      return modelAndView;
  }
  @GetMapping("/home")
 public ModelAndView showListCities(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("home");
      Page<City> cities= cityService.findAll(pageable);
        modelAndView.addObject("cities",cities);
        return modelAndView;
  }
  @GetMapping("/editcity/{id}")
    public ModelAndView showEditPage(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("editcity");
        City cityInRepo= cityService.findCityById(id).orElse(null);
        modelAndView.addObject("city",cityInRepo);
        return modelAndView;
  }
  @PostMapping("/editcity")
    public ModelAndView saveCity(@ModelAttribute City city){
        ModelAndView modelAndView= new ModelAndView("createnewcity");
        cityService.saveCity(city);
          modelAndView.addObject("message","Da cap nhat lai thong tin cua thanh pho");
          modelAndView.addObject("newCity",city);
      return modelAndView;
  }

}