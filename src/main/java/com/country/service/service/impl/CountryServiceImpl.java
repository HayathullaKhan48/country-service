package com.country.service.service.impl;

import com.country.service.entity.CityModel;
import com.country.service.entity.CountryModel;
import com.country.service.entity.StateModel;
import com.country.service.enums.Status;
import com.country.service.exceptions.CountryAlreadyExistsException;
import com.country.service.exceptions.EntityNotFoundException;
import com.country.service.mapper.CountryMapper;
import com.country.service.repository.CityRepository;
import com.country.service.repository.CountryRepository;
import com.country.service.repository.StateRepository;
import com.country.service.request.CountryRequest;
import com.country.service.response.CityResponse;
import com.country.service.response.CountryResponse;
import com.country.service.response.StateResponse;
import com.country.service.service.CountryService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.country.service.mapper.CountryMapper.toCountryModel;
import static com.country.service.mapper.CountryMapper.toCountryResponse;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    public CountryServiceImpl(CountryRepository countryRepository, StateRepository stateRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
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

        return toCountryResponse(savedCountry);
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
        return countryPage.map(CountryMapper::toCountryResponse);
    }

    @Override
    public CountryResponse getCountryByCountryCode(String countryCode) {
        logger.info("Fetching country by code: {}",countryCode);
        CountryModel countryModel = countryRepository.findByCountryCode(countryCode)
                .orElseThrow(()-> new EntityNotFoundException("Country not found with code:"+ countryCode));
        return toCountryResponse(countryModel);
    }

    @Override
    public CountryResponse getCountryByCountryName(String countryName) {
        logger.info("Fetching country by name: {}", countryName);
        CountryModel country = countryRepository.findByCountryName(countryName)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with name: " + countryName));
        return toCountryResponse(country);
    }

    @Override
    public CountryResponse updateCountryByCountryId(Long countryId, CountryRequest request) {
        logger.info("Updating country by id:{}",countryId);
        CountryModel countryModel = countryRepository.findById(countryId)
                .orElseThrow(()-> new EntityNotFoundException("Country not found with id: " + countryId));

        countryModel.setCountryName(request.getCountryName());
        countryModel.setOfficialName(request.getOfficialName());
        countryModel.setIsdCode(request.getIsdCode());
        countryModel.setContinent(request.getContinent());
        countryModel.setCurrencyCode(request.getCurrencyCode());
        countryModel.setCurrencyName(request.getCurrencyName());
        countryModel.setCapital(request.getCapital());
        countryModel.setPopulation(request.getPopulation());
        countryModel.setAreaSqKm(request.getAreaSqKm());

        countryRepository.saveAndFlush(countryModel);
        return toCountryResponse(countryModel);
    }

    @Override
    public CountryResponse deleteCountryByCountryId(Long countryId) {
        logger.info("Soft-deleting country by id: {}", countryId);
        CountryModel country = countryRepository.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + countryId));
        country.setStatus(Status.INACTIVE);
        countryRepository.save(country);
        return toCountryResponse(country);
    }

    @Override
    public List<StateResponse> getAllStates() {
        logger.info("Fetching all states...");
        return countryRepository.findAllStates();
    }

    @Override
    public List<StateResponse> getStatesByCountryId(Long countryId) {
        logger.info("Fetching states for countryId={}", countryId);
        if (!countryRepository.existsById(countryId)) {
            throw new EntityNotFoundException("Country not found with id: " + countryId);
        }
        return countryRepository.findStatesByCountryId(countryId);
    }

    @Override
    public StateResponse getStateByStateCode(String stateCode) {
        return null;
    }

    @Override
    public StateResponse getStateByStateName(String stateName) {
        return null;
    }

    @Override
    public StateResponse getStateByStateCapital(String stateCapital) {
        return null;
    }

    @Override
    public StateResponse updateStateName(Long stateId, String newStateName) {
        return null;
    }

    @Override
    public StateResponse updateStateCapital(Long stateId, String newCapital) {
        return null;
    }

    @Override
    public StateResponse deleteState(Long stateId) {
        return null;
    }

    @Override
    public List<CityResponse> getAllCities() {
        return List.of();
    }

    @Override
    public List<CityResponse> getCitiesByStateId(Long stateId) {
        return List.of();
    }

    @Override
    public CityResponse getCityByCityCode(String cityCode) {
        return null;
    }


}
