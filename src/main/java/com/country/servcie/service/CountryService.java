package com.country.servcie.service;

import com.country.servcie.request.CountryRequest;

public interface CountryService {

    String createCountry(CountryRequest request);

    String getAllCountries();

    String getCountryByCountryCode(String countryCode);

    String getCountryByCountryName(String countryName);

    String getCountryByIsdCode(String isdCode);

    String getCountriesByCurrencyName(String currencyName);

    String getCountryByCapital(String capital);

    String getAllStates();

    String getStatesByCountryCode(String countryCode);

    String getStateByStateCode(String stateCode);

    String getStateByStateName(String stateName);

    String getStateByStateCapital(String stateCapital);

    String getStateByGstCode(String gstCode);

    String getAllCities();

    String getCitiesByStateCode(String stateCode);

    String getCityByCityCode(String cityCode);

    String getCityByCityName(String cityName);

    String getCityByPincode(Integer pincode);
}
