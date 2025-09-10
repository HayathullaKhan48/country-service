package com.country.servcie.repository;

import com.country.servcie.entity.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Long> {

    boolean existsByCountryCode(String countryCode);

    Optional<CountryModel> findByCountryCode(String countryCode);

    Optional<CountryModel> findByCountryName(String countryName);

    @Query("SELECT c FROM CountryModel c WHERE c.status = 'ACTIVE'")
    java.util.List<CountryModel> findAllActiveCountries();
}
