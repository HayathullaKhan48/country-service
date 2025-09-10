package com.country.servcie.service.impl;

import com.country.servcie.entity.CityModel;
import com.country.servcie.entity.CountryModel;
import com.country.servcie.entity.StateModel;
import com.country.servcie.mapper.CountryMapper;
import com.country.servcie.repository.CountryRepository;
import com.country.servcie.request.CityRequest;
import com.country.servcie.request.CountryRequest;
import com.country.servcie.request.StateRequest;
import com.country.servcie.response.CountryResponse;
import com.country.servcie.service.CountryService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.country.servcie.mapper.CountryMapper.toCountryModel;
import static com.country.servcie.mapper.CountryMapper.toCountryResponse;

@Service
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Creates a new country if it doesn't already exist by country code.
     *
     * @param request CountryRequest object from API
     * @return CountryResponse mapped from saved CountryModel
     */
    @Override
    @Transactional
    public CountryResponse createCountry(CountryRequest request) {
        if (countryRepository.existsByCountryCode(request.getCountryCode())) {
            throw new RuntimeException("Country with code " + request.getCountryCode() + " already exists");
        }
        CountryModel countryModel = toCountryModel(request);
        CountryModel savedCountry = countryRepository.save(countryModel);
        List<StateModel> savedStates = new ArrayList<>();
        if (request.getStates() != null) {
            for (StateRequest stateRequest : request.getStates()) {
                StateModel stateModel = CountryMapper.requestToStateMapper(savedCountry, stateRequest);
                if (stateRequest.getCities() != null) {
                    List<CityModel> cityModels = new ArrayList<>();
                    for (CityRequest cityRequest : stateRequest.getCities()) {
                        CityModel cityModel = CountryMapper.requestToCitiesMapper(stateModel, cityRequest);
                        cityModels.add(cityModel);
                    }
                    stateModel.setCities(cityModels);
                }

                savedStates.add(stateModel);
            }
            savedCountry.setStates(savedStates);
        }
        savedCountry = countryRepository.save(savedCountry);
        return CountryMapper.toCountryResponse(savedCountry, savedStates);
    }

    /**
     * Retrieves all countries with pagination and optional sorting by countryCode.
     *
     * @param page Page number (0-based)
     * @param size Number of records per page
     * @return Page of CountryResponse
     */
    @Override
    public Page<CountryResponse> getAllCountries(int page, int size) {
        logger.info("Fetching countries page={} size={}", page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("countryCode").ascending());
        Page<CountryModel> countryPage = countryRepository.findAll(pageable);
        return countryPage.map(model -> toCountryResponse(model, List.of()));
    }

}
