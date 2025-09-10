package com.country.servcie.repository;

import com.country.servcie.entity.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {

    Optional<CityModel> findByCityCode(String cityCode);

    Optional<CityModel> findByCityName(String cityName);

    @Query("SELECT c FROM CityModel c WHERE c.state.stateId = :stateId")
    List<CityModel> findByStateId(@Param("stateId") Long stateId);

    @Query("SELECT c FROM CityModel c WHERE c.status = 'ACTIVE'")
    List<CityModel> findAllActiveCities();
}
