package com.country.servcie.controller;

import com.country.servcie.request.CityRequest;
import com.country.servcie.request.CountryRequest;
import com.country.servcie.request.StateRequest;
import com.country.servcie.response.ApiResponse;
import com.country.servcie.response.CountryResponse;
import com.country.servcie.service.CountryService;
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
    public ResponseEntity<Page<CountryResponse>> getAllCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        logger.info("Fetching countries with page={} and size={}", page, size);
        Page<CountryResponse> countries = countryService.getAllCountries(page,size);
        return ResponseEntity.ok(countries);
    }
}
