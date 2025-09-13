package com.country.service.repository;

import com.country.service.entity.CityModel;
import com.country.service.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Standard JPA repository for CityModel.
 */
@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {

    List<CityModel> findByState_StateId(Long stateId);

    Optional<CityModel> findByCityCode(String cityCode);
}