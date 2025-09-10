package com.country.servcie.repository;

import com.country.servcie.entity.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<StateModel, Long> {

    Optional<StateModel> findByStateCode(String stateCode);

    Optional<StateModel> findByStateName(String stateName);

    @Query("SELECT s FROM StateModel s WHERE s.country.countryId = :countryId")
    List<StateModel> findByCountryId(@Param("countryId") Long countryId);

    @Query("SELECT s FROM StateModel s WHERE s.status = 'ACTIVE'")
    List<StateModel> findAllActiveStates();
}
