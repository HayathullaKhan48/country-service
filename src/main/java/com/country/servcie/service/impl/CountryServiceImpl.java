package com.country.servcie.service.impl;

import com.country.servcie.entity.CityModel;
import com.country.servcie.entity.CountryModel;
import com.country.servcie.entity.StateModel;
import com.country.servcie.exceptions.CountryAlreadyExistsException;
import com.country.servcie.mapper.CountryMapper;
import com.country.servcie.repository.CountryRepository;
import com.country.servcie.request.CityRequest;
import com.country.servcie.request.CountryRequest;
import com.country.servcie.request.StateRequest;
import com.country.servcie.response.CountryResponse;
import com.country.servcie.service.CountryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.country.servcie.mapper.CountryMapper.toCountryModel;
import static com.country.servcie.mapper.CountryMapper.toCountryResponse;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Creates a new country, saves related states and cities.
     *
     * @param request Country details from API request
     * @return {@link CountryResponse} containing saved country, states, and cities
     * @throws CountryAlreadyExistsException if country code already exists
     */
    @Override
    public CountryResponse createCountry(CountryRequest request) {
        logger.info("Create request received for countryCode: {}", request.getCountryCode());

        if (countryRepository.existsByCountryCode(request.getCountryCode())) {
            throw new CountryAlreadyExistsException("Country with code " + request.getCountryCode() + " already exists");
        }
        CountryModel countryModel = toCountryModel(request);
        CountryModel savedCountry = countryRepository.save(countryModel);

        logger.info("Country created (id={} code={})", savedCountry.getCountryId(), savedCountry.getCountryCode());

        List<StateModel> savedStates = new ArrayList<>();
        CountryModel finalSavedCountry = savedCountry;
        request.getStates().forEach(stateRequest -> {
            StateModel stateModel = CountryMapper.requestToStateMapper(finalSavedCountry, stateRequest);

            List<CityModel> cityModels = new ArrayList<>();
            stateRequest.getCities().forEach(cityRequest ->
                    cityModels.add(CountryMapper.requestToCitiesMapper(stateModel, cityRequest))
            );

            stateModel.setCities(cityModels);
            savedStates.add(stateModel);
        });

        savedCountry.setStates(savedStates);
        savedCountry = countryRepository.save(savedCountry);

        logger.info("Country (id={}) created successfully with {} states", savedCountry.getCountryId(), savedStates.size());

        return toCountryResponse(savedCountry, savedStates);
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
