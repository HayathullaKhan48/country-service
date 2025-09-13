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
    @Operation(summary = "Create Country", description = "Create a new country with states and cities")
    public ResponseEntity<CountryResponse> createCountry(@Valid @RequestBody CountryRequest request) {
        logger.info("Received createCountry request for countryCode: {}", request.getCountryCode());
        return ResponseEntity.ok(countryService.createCountry(request));
    }

    @GetMapping("/countries")
    @Operation(summary = "Get All Countries", description = "Retrieve all countries with nested states and cities (paginated)")
    public ResponseEntity<Page<CountryResponse>> getAllCountries(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching countries with page={} and size={}", page, size);
        Page<CountryResponse> countries = countryService.getAllCountries(page, size);
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/getCountryByCountryCode/{countryCode}")
    @Operation(summary = "Get Country by Code", description = "Retrieve a country using its countryCode")
    public ResponseEntity<CountryResponse> getCountryByCode(@PathVariable String countryCode) {
        logger.info("Fetching country by code: {}", countryCode);
        return ResponseEntity.ok(countryService.getCountryByCountryCode(countryCode));
    }

    @GetMapping("/getCountryByCountryName/{countryName}")
    @Operation(summary = "Get Country by Name", description = "Retrieve a country using its countryName")
    public ResponseEntity<CountryResponse> getCountryByName(@PathVariable String countryName) {
        logger.info("Fetching country by name: {}", countryName);
        return ResponseEntity.ok(countryService.getCountryByCountryName(countryName));
    }

    @DeleteMapping("/deleteCountryByCountryId/{countryId}")
    @Operation(summary = "Delete Country (Soft)", description = "Marks the country as inactive instead of deleting permanently")
    public ResponseEntity<CountryResponse> deleteCountry(@PathVariable Long countryId) {
        logger.info("Soft-deleting (mark inactive) country by id: {}", countryId);
        return ResponseEntity.ok(countryService.deleteCountryByCountryId(countryId));
    }

    @GetMapping("/getStates")
    @Operation(summary = "Get All States", description = "Retrieve all states from all countries")
    public ResponseEntity<List<StateResponse>> getAllStates() {
        logger.info("Fetching all states");
        return ResponseEntity.ok(countryService.getAllStates());
    }

    @GetMapping("/getStatesByCountryId/{countryId}")
    @Operation(summary = "Get States by CountryId", description = "Retrieve all states belonging to a given country")
    public ResponseEntity<List<StateResponse>> getStatesByCountryId(@PathVariable Long countryId) {
        logger.info("Fetching states by countryId={}", countryId);
        return ResponseEntity.ok(countryService.getStatesByCountryId(countryId));
    }

    @GetMapping("/getStateByStateCode/{stateCode}")
    @Operation(summary = "Get State by Code", description = "Retrieve a single state using its stateCode")
    public StateResponse getStateByStateCode(@PathVariable String stateCode) {
        logger.info("Fetching state by stateCode={}", stateCode);
        return countryService.getStateByStateCode(stateCode);
    }

    @GetMapping("/getStateByStateName/{stateName}")
    @Operation(summary = "Get State by Name", description = "Retrieve a single state using its stateName")
    public StateResponse getStateByStateName(@PathVariable String stateName) {
        logger.info("Fetching state by stateName={}", stateName);
        return countryService.getStateByStateName(stateName);
    }

    @GetMapping("/getStateByStateCapital/{stateCapital}")
    @Operation(summary = "Get State by Capital", description = "Retrieve a state by its capital city")
    public StateResponse getStateByStateCapital(@PathVariable String stateCapital) {
        logger.info("Fetching state by stateCapital={}", stateCapital);
        return countryService.getStateByStateCapital(stateCapital);
    }

    @PatchMapping("/updateStateByStateName/{stateId}/{newStateName}")
    @Operation(summary = "Update State Name", description = "Updates the name of a state by stateId")
    public StateResponse updateStateName(@PathVariable Long stateId, @PathVariable String newStateName) {
        logger.info("Updating state name for stateId={} to newName={}", stateId, newStateName);
        return countryService.updateStateName(stateId, newStateName);
    }

    @PatchMapping("/updateStateByCapital/{stateId}/{newCapital}")
    @Operation(summary = "Update State Capital", description = "Updates the capital of a state by stateId")
    public StateResponse updateStateCapital(@PathVariable Long stateId, @PathVariable String newCapital) {
        logger.info("Updating state capital for stateId={} to newCapital={}", stateId, newCapital);
        return countryService.updateStateCapital(stateId, newCapital);
    }

    @DeleteMapping("/deleteState/{stateId}")
    @Operation(summary = "Delete State (Soft)", description = "Marks a state as inactive instead of deleting permanently")
    public StateResponse deleteState(@PathVariable Long stateId) {
        logger.info("Soft-deleting (mark inactive) state with stateId={}", stateId);
        return countryService.deleteState(stateId);
    }

    @GetMapping("/getCities")
    @Operation(summary = "Get All Cities", description = "Retrieve all cities from all states")
    public List<CityResponse> getAllCities() {
        logger.info("Fetching all cities");
        return countryService.getAllCities();
    }

    @GetMapping("/getCitiesByStateId/{stateId}")
    @Operation(summary = "Get Cities by StateId", description = "Retrieve all cities belonging to a specific state")
    public List<CityResponse> getCitiesByStateId(@PathVariable Long stateId) {
        logger.info("Fetching cities by stateId={}", stateId);
        return countryService.getCitiesByStateId(stateId);
    }

    @GetMapping("getCitiesByCityCode/{cityCode}")
    @Operation(summary = "Get City by CityCode", description = "Retrieve a single city using its cityCode")
    public CityResponse getCitiesByCityCode(@PathVariable String cityCode) {
        logger.info("Fetching city by cityCode={}", cityCode);
        return countryService.getCitiesByCityCode(cityCode);
    }
}
