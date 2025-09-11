package com.country.service.repository;

import com.country.service.entity.CityModel;
import com.country.service.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Standard JPA repository for CityModel.
 */
@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {

    Optional<CityModel> findByCityCodeAndStatus(String cityCode, Status status);

    Optional<CityModel> findByCityNameAndStatus(String cityName, Status status);

    List<CityModel> findByState_StateIdAndStatus(Long stateId, Status status);

    boolean existsByCityName(String cityName);

    List<CityModel> findByStatus(Status status);
}
