package com.country.servcie.service;

import com.country.servcie.request.CountryRequest;
import com.country.servcie.response.CountryResponse;
import org.springframework.data.domain.Page;

public interface CountryService {

    CountryResponse createCountry(CountryRequest request);

    Page<CountryResponse> getAllCountries(int pag, int size);

}
