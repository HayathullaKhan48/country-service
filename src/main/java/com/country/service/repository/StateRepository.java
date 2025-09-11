package com.country.service.repository;

import com.country.service.entity.StateModel;
import com.country.service.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Standard JPA repository for StateModel.
 * Uses query-derivation for Status-based finders.
 */
@Repository
public interface StateRepository extends JpaRepository<StateModel, Long> {

    Optional<StateModel> findByStateCodeAndStatus(String stateCode, Status status);

    Optional<StateModel> findByStateNameAndStatus(String stateName, Status status);

    Optional<StateModel> findByStateCapitalAndStatus(String stateCapital, Status status);

    Optional<StateModel> findById(Long stateId);

    boolean existsByStateName(String stateName);

}
