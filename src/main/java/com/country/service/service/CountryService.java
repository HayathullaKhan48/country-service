package com.country.service.service;

import com.country.service.request.CountryRequest;
import com.country.service.response.CityResponse;
import com.country.service.response.CountryResponse;
import com.country.service.response.StateResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {

    CountryResponse createCountry(CountryRequest request);

    Page<CountryResponse> getAllCountries(int pag, int size);

    CountryResponse getCountryByCountryCode(String countryCode);

    CountryResponse getCountryByCountryName(String countryName);

    CountryResponse updateCountryByCountryId(Long countryId, CountryRequest request);

    CountryResponse deleteCountryByCountryId(Long countryId);

    List<StateResponse> getAllStates();

    List<StateResponse> getStatesByCountryId(Long countryId);

    StateResponse getStateByStateCode(String stateCode);

    StateResponse getStateByStateName(String stateName);

    StateResponse getStateByStateCapital(String stateCapital);

    StateResponse updateStateName(Long stateId, String newStateName);

    StateResponse updateStateCapital(Long stateId, String newCapital);

    StateResponse deleteState(Long stateId);

    List<CityResponse> getAllCities();

    List<CityResponse> getCitiesByStateId(Long stateId);

    CityResponse getCityByCityCode(String cityCode);

}
