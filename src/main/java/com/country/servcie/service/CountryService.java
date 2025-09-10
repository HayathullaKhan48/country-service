package com.country.servcie.service;

import com.country.servcie.request.CityRequest;
import com.country.servcie.request.CountryRequest;
import com.country.servcie.request.StateRequest;

import java.util.List;

/**
 * Service interface for Country, State, and City operations.
 * Defines all business logic method signatures.
 */
public interface CountryService {

    String createCountry(CountryRequest request);

    List<CountryRequest> getAllCountries();

    CountryRequest getCountryByCountryCode(String countryCode);

    CountryRequest getCountryByCountryName(String countryName);

    String updateCountryByCountryId(Long countryId, CountryRequest request);

    String deleteCountryByCountryId(Long countryId);

    List<StateRequest> getAllStates();

    List<StateRequest> getStateByCountryId(Long countryId);

    StateRequest getStateByStateCode(String stateCode);

    StateRequest getStateByStateName(String stateName);

    StateRequest getStateByStateCapital(String stateCapital);

    String updateStateName(Long stateId, String newStateName);

    String updateStateCapital(Long stateId, String newCapital);

    String deleteState(Long stateId);

    List<CityRequest> getAllCities();

    List<CityRequest> getCitiesByStateId(Long stateId);

    CityRequest getCitiesByCityCode(String cityCode);
}
