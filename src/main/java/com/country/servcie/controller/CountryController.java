package com.country.servcie.controller;

import com.country.servcie.request.CityRequest;
import com.country.servcie.request.CountryRequest;
import com.country.servcie.request.StateRequest;
import com.country.servcie.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Countries, States and Cities.
 * All endpoints return String response for easy testing in Postman.
 */
@RestController
@RequestMapping("/api/country/v1")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/create")
    public String createCountry(@RequestBody CountryRequest request) {
        return countryService.createCountry(request);
    }

    @GetMapping("/countries")
    public List<CountryRequest> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/getCountryByCountryCode/{countryCode}")
    public CountryRequest getCountryByCountryCode(@PathVariable String countryCode) {
        return countryService.getCountryByCountryCode(countryCode);
    }

    @GetMapping("/getCountryByCountryName/{countryName}")
    public CountryRequest getCountryByCountryName(@PathVariable String countryName) {
        return countryService.getCountryByCountryName(countryName);
    }

    @PutMapping("/updateCountryByCountryId/{countryId}")
    public String updateCountryByCountryId(@PathVariable Long countryId,
                                           @RequestBody CountryRequest request) {
        return countryService.updateCountryByCountryId(countryId, request);
    }

    @DeleteMapping("/deleteCountryByCountryId/{countryId}")
    public String deleteCountryByCountryId(@PathVariable Long countryId) {
        return countryService.deleteCountryByCountryId(countryId);
    }

    @GetMapping("/getStates")
    public List<StateRequest> getAllStates() {
        return countryService.getAllStates();
    }

    @GetMapping("/getStateByCountryId/{countryId}")
    public List<StateRequest> getStateByCountryId(@PathVariable Long countryId) {
        return countryService.getStateByCountryId(countryId);
    }

    @GetMapping("/getStateByStateCode/{stateCode}")
    public StateRequest getStateByStateCode(@PathVariable String stateCode) {
        return countryService.getStateByStateCode(stateCode);
    }

    @GetMapping("/getStateByStateName/{stateName}")
    public StateRequest getStateByStateName(@PathVariable String stateName) {
        return countryService.getStateByStateName(stateName);
    }

    @GetMapping("/getStateByStateCapital/{stateCapital}")
    public StateRequest getStateByStateCapital(@PathVariable String stateCapital) {
        return countryService.getStateByStateCapital(stateCapital);
    }

    @PatchMapping("/updateStateByStateName/{stateId}/{newStateName}")
    public String updateStateName(@PathVariable Long stateId, @PathVariable String newStateName) {
        return countryService.updateStateName(stateId, newStateName);
    }

    @PatchMapping("/updateStateByCapital/{stateId}/{newCapital}")
    public String updateStateCapital(@PathVariable Long stateId, @PathVariable String newCapital) {
        return countryService.updateStateCapital(stateId, newCapital);
    }

    @DeleteMapping("/deleteState/{stateId}")
    public String deleteState(@PathVariable Long stateId) {
        return countryService.deleteState(stateId);
    }

    @GetMapping("/getCities")
    public List<CityRequest> getAllCities(){
        return countryService.getAllCities();
    }

    @GetMapping("/getCitiesByStateId/{stateId)")
    public List<CityRequest> getCitiesByStateId(@PathVariable Long stateId){
        return countryService.getCitiesByStateId(stateId);
    }

    @GetMapping("/getCitiesByCityCode/{cityCode}")
    public CityRequest getCitiesByCityCode(@PathVariable String cityCode){
        return countryService.getCitiesByCityCode(cityCode);
    }
}
