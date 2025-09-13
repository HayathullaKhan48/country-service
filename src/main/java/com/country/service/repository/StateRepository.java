package com.country.service.repository;

import com.country.service.entity.StateModel;
import com.country.service.enums.Status;
import com.country.service.response.StateResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Standard JPA repository for StateModel.
 * Uses query-derivation for Status-based finders.
 */
@Repository
public interface StateRepository extends JpaRepository<StateModel, Long> {

    List<StateModel> findByCountry_CountryId(Long countryId);

    Optional<StateModel> findByStateCodeIgnoreCase(String stateCode);

    Optional<StateModel> findByStateName(String stateName);

    Optional<StateModel> findByStateCapitalIgnoreCase(String stateCapital);

}
