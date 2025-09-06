package com.country.servcie.controller;

import com.country.servcie.request.CountryRequest;
import com.country.servcie.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing Countries, States and Cities.
 * All endpoints return String response for easy testing in Postman.
 */
@RestController
@RequestMapping("/api/country/v1")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    /**
     * create a new Country.
     *
     * @param request The country details in request body.
     * @return String message confirming country creation.
     */
    @PostMapping("/create")
    public String createCountry(@RequestBody CountryRequest request) {
        return countryService.createCountry(request);
    }

    /**
     * Get all countries.
     *
     * @return String containing a list of all countries.
     */
    @GetMapping("/countries")
    public String getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/getCountryByCountryCode/{countryCode}")
    public String getCountryByCountryCode(@PathVariable String countryCode) {
        return countryService.getCountryByCountryCode(countryCode);
    }

    @GetMapping("/getCountryByCountryName/{countryName}")
    public String getCountryByCountryName(@PathVariable String countryName) {
        return countryService.getCountryByCountryName(countryName);
    }

    @GetMapping("/getCountryByIsdCode/{isdCode}")
    public String getCountryByIsdCode(@PathVariable String isdCode) {
        return countryService.getCountryByIsdCode(isdCode);
    }

    @GetMapping("/getCountriesByCurrencyName/{currencyName}")
    public String getCountriesByCurrencyName(@PathVariable String currencyName) {
        return countryService.getCountriesByCurrencyName(currencyName);
    }

    @GetMapping("/getCountryByCapital/{capital}")
    public String getCountryByCapital(@PathVariable String capital) {
        return countryService.getCountryByCapital(capital);
    }

    @GetMapping("/getAllStates")
    public String getAllStates() {
        return countryService.getAllStates();
    }

    @GetMapping("/getStatesByCountryCode/{countryCode}")
    public String getStatesByCountryCode(@PathVariable String countryCode) {
        return countryService.getStatesByCountryCode(countryCode);
    }

    @GetMapping("/getStateByStateCode/{stateCode}")
    public String getStateByStateCode(@PathVariable String stateCode) {
        return countryService.getStateByStateCode(stateCode);
    }

    @GetMapping("/getStateByStateName/{stateName}")
    public String getStateByStateName(@PathVariable String stateName) {
        return countryService.getStateByStateName(stateName);
    }

    @GetMapping("/getStateByStateCapital/{stateCapital}")
    public String getStateByStateCapital(@PathVariable String stateCapital) {
        return countryService.getStateByStateCapital(stateCapital);
    }

    @GetMapping("/getStateByGstCode/{gstCode}")
    public String getStateByGstCode(@PathVariable String gstCode) {
        return countryService.getStateByGstCode(gstCode);
    }

    @GetMapping("/getAllCities")
    public String getAllCities() {
        return countryService.getAllCities();
    }

    @GetMapping("/getCitiesByStateCode/{stateCode}")
    public String getCitiesByStateCode(@PathVariable String stateCode) {
        return countryService.getCitiesByStateCode(stateCode);
    }

    @GetMapping("/getCityByCityCode/{cityCode}")
    public String getCityByCityCode(@PathVariable String cityCode){
        return countryService.getCityByCityCode(cityCode);
    }

    @GetMapping("/getCityByCityName/{cityName}")
    public String getCityByCityName(@PathVariable String cityName){
        return countryService.getCityByCityName(cityName);
    }

    @GetMapping("/getCityByPincode/{pincode}")
    public String getCityByPincode(@PathVariable Integer pincode){
        return countryService.getCityByPincode(pincode);
    }
}
