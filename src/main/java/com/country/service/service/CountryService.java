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

    CountryResponse deleteCountryByCountryId(Long countryId);

    List<StateResponse> getAllStates();

    List<StateResponse> getStatesByCountryId(Long countryId);

    List<StateResponse> getStateByStateCode(String stateCode);

    List<StateResponse> getStateByStateName(String stateName);

    List<StateResponse> getStateByStateCapital(String stateCapital);

    List<StateResponse> updateStateByStateName(Long stateId, String newStateName);

    List<StateResponse> updateStateByCapital(Long stateId, String newCapital);

    List<StateResponse> deleteState(Long stateId);

    List<CityResponse> getAllCities();

    List<CityResponse> getCitiesByStateId(Long stateId);

    List<CityResponse> getCitiesByCityCode(String cityCode);
}
