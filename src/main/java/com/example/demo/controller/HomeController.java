package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.CityService;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
      modelAndView.addObject("city",new City());
      return modelAndView;
    }
  @PostMapping("/createNewCity")
  public ModelAndView saveNewCity(@Validated @ModelAttribute City city, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            
            return new ModelAndView("createnewcity");
        } else {
            cityService.saveCity(city);
            ModelAndView modelAndView = new ModelAndView("createnewcity");
            modelAndView.addObject("message","Tao moi thanh pho thanh cong");
            return modelAndView;
        }


//      ModelAndView modelAndView = new ModelAndView("createnewcity");

//     String cityName= newCity.getName();
//     Optional<City> cityInRepo = cityService.findByName(cityName);
//     if (cityInRepo.isPresent()||bindingResult.hasFieldErrors()){
//       modelAndView.addObject("message","City is ready in system");
//       modelAndView.addObject("newCity",newCity);
//     } else {
//         cityService.saveCity(newCity);
//       modelAndView.addObject("message","City create success");
//       modelAndView.addObject("newCity",newCity);
//     }
//      return modelAndView;
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
  @GetMapping("/removecity/{id}")
    public ModelAndView showDeletePage(@PathVariable Long id){

    ModelAndView modelAndView = new ModelAndView("deletecity");
    City city= cityService.findCityById(id).orElse(null);
    modelAndView.addObject("city",city);
    return modelAndView;
  }
  @PostMapping("/removecity")
    public ModelAndView deleteCity(@ModelAttribute City city){
        cityService.moveCity(city.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
  }
  @GetMapping("/viewcity/{id}")
    public ModelAndView showCity(@PathVariable Long id){
        City city= cityService.findCityById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("viewcity");
        modelAndView.addObject("city",city);
        return modelAndView;

  }

}