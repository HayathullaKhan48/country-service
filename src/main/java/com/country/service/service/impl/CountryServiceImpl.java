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

import static com.country.service.mapper.CountryMapper.*;

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

    @Override
    public CountryResponse createCountry(CountryRequest request) {
        logger.info("Create request received for countryCode: {}", request.getCountryCode());

        if (countryRepository.existsByCountryCode(request.getCountryCode())) {
            logger.error("Country with code {} already exists", request.getCountryCode());
            throw new CountryAlreadyExistsException("Country with code " + request.getCountryCode() + " already exists");
        }

        CountryModel countryModel = toCountryModel(request);
        CountryModel savedCountry = countryRepository.save(countryModel);
        logger.info("Country created successfully (id={} code={})", savedCountry.getCountryId(), savedCountry.getCountryCode());

        List<StateModel> savedStates = new ArrayList<>();
        CountryModel finalSavedCountry = savedCountry;

        request.getStates().forEach(stateRequest -> {
            StateModel stateModel = CountryMapper.requestToStateMapper(finalSavedCountry, stateRequest);

            List<CityModel> cityModels = new ArrayList<>();
            stateRequest.getCities().forEach(cityRequest -> {
                cityModels.add(CountryMapper.requestToCitiesMapper(stateModel, cityRequest));
                logger.debug("City mapped for state: {} -> cityCode={}", stateRequest.getStateName(), cityRequest.getCityCode());
            });

            stateModel.setCities(cityModels);
            savedStates.add(stateModel);
            logger.debug("State added to country: {}", stateRequest.getStateName());
        });

        savedCountry.setStates(savedStates);
        savedCountry = countryRepository.saveAndFlush(savedCountry);
        logger.info("Country (id={}) saved with {} states", savedCountry.getCountryId(), savedStates.size());

        return CountryMapper.toCountryResponse(savedCountry);
    }

    @Override
    public Page<CountryResponse> getAllCountries(int page, int size) {
        logger.info("Fetching countries page={} size={}", page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("countryCode").ascending());
        Page<CountryModel> countryPage = countryRepository.findAll(pageable);
        logger.debug("Fetched {} countries", countryPage.getTotalElements());
        return countryPage.map(CountryMapper::toCountryResponse);
    }

    @Override
    public CountryResponse getCountryByCountryCode(String countryCode) {
        logger.info("Fetching country by code: {}", countryCode);
        CountryModel countryModel = countryRepository.findByCountryCode(countryCode)
                .orElseThrow(() -> {
                    logger.error("Country not found with code: {}", countryCode);
                    return new EntityNotFoundException("Country not found with code:" + countryCode);
                });
        return toCountryResponse(countryModel);
    }

    @Override
    public CountryResponse getCountryByCountryName(String countryName) {
        logger.info("Fetching country by name: {}", countryName);
        CountryModel country = countryRepository.findByCountryName(countryName)
                .orElseThrow(() -> {
                    logger.error("Country not found with name: {}", countryName);
                    return new EntityNotFoundException("Country not found with name: " + countryName);
                });
        return toCountryResponse(country);
    }

    @Override
    public CountryResponse deleteCountryByCountryId(Long countryId) {
        logger.info("Soft delete request received for countryId={}", countryId);
        CountryModel country = countryRepository.findById(countryId)
                .orElseThrow(() -> {
                    logger.error("Country not found with id: {}", countryId);
                    return new EntityNotFoundException("Country not found with id: " + countryId);
                });

        country.setStatus(Status.INACTIVE);
        if (country.getStates() != null) {
            country.getStates().forEach(state -> {
                state.setStatus(Status.INACTIVE);
                if (state.getCities() != null) {
                    state.getCities().forEach(city -> city.setStatus(Status.INACTIVE));
                }
            });
        }
        CountryModel updatedCountry = countryRepository.saveAndFlush(country);
        logger.info("Country (id={}) marked as INACTIVE successfully", updatedCountry.getCountryId());
        return CountryMapper.toCountryResponse(updatedCountry);
    }

    @Override
    public List<StateResponse> getAllStates() {
        logger.info("Fetching all states...");
        List<StateModel> stateModels = stateRepository.findAll();
        logger.debug("Found {} states", stateModels.size());
        return CountryMapper.modelToStatesResponse(stateModels);
    }

    @Override
    public List<StateResponse> getStatesByCountryId(Long countryId) {
        logger.info("Fetching states for countryId={}", countryId);
        List<StateModel> stateModels = stateRepository.findByCountry_CountryId(countryId);
        if (stateModels.isEmpty()) {
            logger.warn("No states found for countryId={}", countryId);
            return List.of();
        }
        return CountryMapper.modelToStatesResponse(stateModels);
    }

    @Override
    public StateResponse getStateByStateCode(String stateCode) {
        logger.info("Fetching state by stateCode={}", stateCode);
        StateModel stateModel = stateRepository.findByStateCodeIgnoreCase(stateCode)
                .orElseThrow(() -> {
                    logger.error("State not found with stateCode: {}", stateCode);
                    return new RuntimeException("State not found with stateCode: " + stateCode);
                });
        return CountryMapper.modelToStatesResponse(List.of(stateModel)).get(0);
    }

    @Override
    public StateResponse getStateByStateName(String stateName) {
        logger.info("Fetching state by stateName={}", stateName);
        StateModel stateModel = stateRepository.findByStateName(stateName)
                .orElseThrow(() -> {
                    logger.error("State not found with stateName: {}", stateName);
                    return new RuntimeException("State not found with stateName: " + stateName);
                });
        return CountryMapper.modelToStatesResponse(List.of(stateModel)).get(0);
    }

    @Override
    public StateResponse getStateByStateCapital(String stateCapital) {
        logger.info("Fetching state by stateCapital={}", stateCapital);
        StateModel stateModel = stateRepository.findByStateCapitalIgnoreCase(stateCapital)
                .orElseThrow(() -> {
                    logger.error("State not found with stateCapital: {}", stateCapital);
                    return new RuntimeException("State not found with stateCapital: " + stateCapital);
                });
        return CountryMapper.modelToStatesResponse(List.of(stateModel)).get(0);
    }

    @Override
    public StateResponse updateStateName(Long stateId, String newStateName) {
        logger.info("Updating stateName for stateId={} to {}", stateId, newStateName);
        StateModel stateModel = stateRepository.findById(stateId)
                .orElseThrow(() -> {
                    logger.error("State not found with id: {}", stateId);
                    return new RuntimeException("State not found with id: " + stateId);
                });
        stateModel.setStateName(newStateName);
        StateModel updatedState = stateRepository.saveAndFlush(stateModel);
        logger.info("State (id={}) updated with new stateName={}", stateId, newStateName);
        return CountryMapper.modelToStatesResponse(List.of(updatedState)).get(0);
    }

    @Override
    public StateResponse updateStateCapital(Long stateId, String newCapital) {
        logger.info("Updating stateCapital for stateId={} to {}", stateId, newCapital);
        StateModel stateModel = stateRepository.findById(stateId)
                .orElseThrow(() -> {
                    logger.error("State not found with stateId: {}", stateId);
                    return new RuntimeException("State not found with stateId: " + stateId);
                });
        stateModel.setStateCapital(newCapital);
        StateModel updatedState = stateRepository.saveAndFlush(stateModel);
        logger.info("State (id={}) updated with new stateCapital={}", stateId, newCapital);
        return CountryMapper.modelToStatesResponse(List.of(updatedState)).get(0);
    }

    @Override
    public StateResponse deleteState(Long stateId) {
        logger.info("Soft deleting stateId={}", stateId);
        StateModel stateModel = stateRepository.findById(stateId)
                .orElseThrow(() -> {
                    logger.error("State not found with stateId: {}", stateId);
                    return new RuntimeException("State not found with stateId: " + stateId);
                });
        stateModel.setStatus(Status.INACTIVE);
        StateModel updatedState = stateRepository.saveAndFlush(stateModel);
        logger.info("State (id={}) marked as INACTIVE successfully", stateId);
        return CountryMapper.modelToStatesResponse(List.of(updatedState)).get(0);
    }

    @Override
    public List<CityResponse> getAllCities() {
        logger.info("Fetching all cities...");
        List<CityModel> cityModels = cityRepository.findAll();
        if (cityModels.isEmpty()) {
            logger.warn("No cities found in the database");
            return List.of();
        }
        logger.debug("Found {} cities", cityModels.size());
        return CountryMapper.modelToCitiesResponse(cityModels);
    }

    @Override
    public List<CityResponse> getCitiesByStateId(Long stateId) {
        logger.info("Fetching cities for stateId={}", stateId);
        List<CityModel> cityModels = cityRepository.findByState_StateId(stateId);
        if (cityModels.isEmpty()) {
            logger.warn("No cities found for stateId={}", stateId);
            return List.of();
        }
        return CountryMapper.modelToCitiesResponse(cityModels);
    }

    @Override
    public CityResponse getCitiesByCityCode(String cityCode) {
        logger.info("Fetching city by cityCode={}", cityCode);
        CityModel cityModel = cityRepository.findByCityCode(cityCode)
                .orElseThrow(() -> {
                    logger.error("City not found with cityCode: {}", cityCode);
                    return new RuntimeException("City not found with cityCode:" + cityCode);
                });
        return CountryMapper.modelToCitiesResponse(List.of(cityModel)).get(0);
    }
}
