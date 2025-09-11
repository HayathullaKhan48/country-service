package com.country.service.controller;

import com.country.service.request.CountryRequest;
import com.country.service.response.CityResponse;
import com.country.service.response.CountryResponse;
import com.country.service.response.StateResponse;
import com.country.service.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    /**
     * Create a new country with optional states and cities.
     *
     * @param request CountryRequest
     * @return CountryResponse
     */
    @PostMapping("/create")
    @Operation(summary = "Create Country", description = "create a new country with states and cities")
    public ResponseEntity<CountryResponse> createCountry(@Valid @RequestBody CountryRequest request) {
        logger.info("Received createCountry request for countryCode: {}",request.getCountryCode());
        return ResponseEntity.ok(countryService.createCountry(request));
    }

    /**
     * Retrieve a paginated list of all countries.
     *
     * @param page page number (default 0)
     * @param size page size (default 10)
     * @return Page of CountryResponse
     */
    @GetMapping("/countries")
    @Operation(summary = "Get All Countries", description = "Retrieve all countries with nested states and cities")
    public ResponseEntity<Page<CountryResponse>> getAllCountries(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size){
        logger.info("Fetching countries with page={} and size={}", page, size);
        Page<CountryResponse> countries = countryService.getAllCountries(page,size);
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/getCountryByCountryCode/{countryCode}")
    public ResponseEntity<CountryResponse> getCountryByCode(@PathVariable String countryCode) {
        logger.info("Fetching country by code: {}", countryCode);
        return ResponseEntity.ok(countryService.getCountryByCountryCode(countryCode));
    }

    @GetMapping("/getCountryByCountryName/{countryName}")
    public ResponseEntity<CountryResponse> getCountryByName(@PathVariable String countryName) {
        logger.info("Fetching country by name: {}", countryName);
        return ResponseEntity.ok(countryService.getCountryByCountryName(countryName));
    }

    @PutMapping("/updateCountryByCountryId/{countryId}")
    public ResponseEntity<CountryResponse> updateCountry(@PathVariable Long countryId,
                                                         @Valid @RequestBody CountryRequest request) {
        logger.info("Updating country by id: {}", countryId);
        return ResponseEntity.ok(countryService.updateCountryByCountryId(countryId, request));
    }

    @DeleteMapping("/deleteCountryByCountryId/{countryId}")
    public ResponseEntity<CountryResponse> deleteCountry(@PathVariable Long countryId) {
        logger.info("Soft-deleting (mark inactive) country by id: {}", countryId);
        return ResponseEntity.ok(countryService.deleteCountryByCountryId(countryId));
    }

    @GetMapping("/getStates")
    public ResponseEntity<List<StateResponse>> getAllStates() {
        logger.info("Fetching all states...");
        return ResponseEntity.ok(countryService.getAllStates());
    }

    @GetMapping("/getStatesByCountryId/{countryId}")
    public ResponseEntity<List<StateResponse>> getStatesByCountryId(@PathVariable Long countryId) {
        logger.info("Fetching states by country id: {}", countryId);
        return ResponseEntity.ok(countryService.getStatesByCountryId(countryId));
    }

    @GetMapping("/getStateByStateCode/{stateCode}")
    public ResponseEntity<StateResponse> getStateByCode(@PathVariable String stateCode) {
        logger.info("Fetching state by code: {}", stateCode);
        return ResponseEntity.ok(countryService.getStateByStateCode(stateCode));
    }

    @GetMapping("/getStateByStateName/{stateName}")
    public ResponseEntity<StateResponse> getStateByName(@PathVariable String stateName) {
        logger.info("Fetching state by name: {}", stateName);
        return ResponseEntity.ok(countryService.getStateByStateName(stateName));
    }

    @GetMapping("/getStateByStateCapital/{stateCapital}")
    public ResponseEntity<StateResponse> getStateByCapital(@PathVariable String stateCapital) {
        logger.info("Fetching state by capital: {}", stateCapital);
        return ResponseEntity.ok(countryService.getStateByStateCapital(stateCapital));
    }

    @PatchMapping("/updateStateByStateName/{stateId}/{newStateName}")
    public ResponseEntity<StateResponse> updateStateName(@PathVariable Long stateId, @PathVariable String newStateName) {
        logger.info("Updating state name for id: {}", stateId);
        return ResponseEntity.ok(countryService.updateStateName(stateId, newStateName));
    }

    @PatchMapping("/updateStateByCapital/{stateId}/{newCapital}")
    public ResponseEntity<StateResponse> updateStateCapital(@PathVariable Long stateId, @PathVariable String newCapital) {
        logger.info("Updating state capital for id: {}", stateId);
        return ResponseEntity.ok(countryService.updateStateCapital(stateId, newCapital));
    }

    @DeleteMapping("/deleteState/{stateId}")
    public ResponseEntity<StateResponse> deleteState(@PathVariable Long stateId) {
        logger.info("Soft-deleting (mark inactive) state by id: {}", stateId);
        return ResponseEntity.ok(countryService.deleteState(stateId));
    }

    @GetMapping("/getCities")
    public ResponseEntity<List<CityResponse>> getAllCities(){
        logger.info("Fetching All cities...");
        return ResponseEntity.ok(countryService.getAllCities());
    }

    @GetMapping("/getCitiesByStateId/{stateId}")
    public ResponseEntity<List<CityResponse>> getCitiesByStateId(@PathVariable Long stateId){
        logger.info("Fetching cities by state id: {}",stateId);
        return ResponseEntity.ok(countryService.getCitiesByStateId(stateId));
    }

    @GetMapping("/getCitiesByCityCode/{cityCode}")
    public ResponseEntity<CityResponse> getCityByCode(@PathVariable String cityCode){
        logger.info("Fetching city by code: {}", cityCode);
        return ResponseEntity.ok(countryService.getCityByCityCode(cityCode));
    }
}
