package com.country.service.repository;

import com.country.service.entity.CountryModel;
import com.country.service.entity.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CountryRepository - provides CRUD operations for CountryModel
 * and custom queries for State projections.
 */
@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Long> {

    Optional<CountryModel> findByCountryCode(String countryCode);

    Optional<CountryModel> findByCountryName(String countryName);

    boolean existsByCountryCode(String countryCode);


}
