package com.country.service.repository;

import com.country.service.entity.CityModel;
import com.country.service.entity.CountryModel;
import com.country.service.entity.StateModel;
import com.country.service.response.CityResponse;
import com.country.service.response.StateResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CountryRepository - contains country CRUD plus projections for states/cities.
 * All JPQL projections use com.country.service.response.* package constructors.
 */
@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Long> {

    Optional<CountryModel> findByCountryCode(String countryCode);

    Optional<CountryModel> findByCountryName(String countryName);

    boolean existsByCountryCode(String countryCode);

    @Query("SELECT new com.country.service.response.StateResponse(s.stateId, s.stateName, s.stateCapital, s.stateCode) " +
            "FROM StateModel s WHERE s.status = com.country.service.enums.Status.ACTIVE")
    List<StateResponse> findAllStates();

    @Query("SELECT new com.country.service.response.StateResponse(s.stateId, s.stateName, s.stateCapital, s.stateCode) " +
            "FROM StateModel s WHERE s.country.countryId = :countryId AND s.status = com.country.service.enums.Status.ACTIVE")
    List<StateResponse> findStatesByCountryId(Long countryId);

    @Query("SELECT s FROM StateModel s WHERE s.stateCode = :stateCode AND s.status = com.country.service.enums.Status.ACTIVE")
    Optional<StateModel> findStateByStateCode(String stateCode);

    @Query("SELECT s FROM StateModel s WHERE s.stateName = :stateName AND s.status = com.country.service.enums.Status.ACTIVE")
    Optional<StateModel> findStateByStateName(String stateName);

    @Query("SELECT s FROM StateModel s WHERE s.stateCapital = :stateCapital AND s.status = com.country.service.enums.Status.ACTIVE")
    Optional<StateModel> findStateByStateCapital(String stateCapital);

    @Query("SELECT s FROM StateModel s WHERE s.stateId = :stateId AND s.status = com.country.service.enums.Status.ACTIVE")
    Optional<StateModel> findStateById(Long stateId);

    @Query("SELECT new com.country.service.response.CityResponse(c.cityId, c.cityName, c.cityCode) " +
            "FROM CityModel c WHERE c.status = com.country.service.enums.Status.ACTIVE")
    List<CityResponse> findAllCities();

    @Query("SELECT new com.country.service.response.CityResponse(c.cityId, c.cityName, c.cityCode) " +
            "FROM CityModel c WHERE c.state.stateId = :stateId AND c.status = com.country.service.enums.Status.ACTIVE")
    List<CityResponse> findCitiesByStateId(Long stateId);

    @Query("SELECT c FROM CityModel c WHERE c.cityCode = :cityCode AND c.status = com.country.service.enums.Status.ACTIVE")
    Optional<CityModel> findCityByCityCode(String cityCode);
}
