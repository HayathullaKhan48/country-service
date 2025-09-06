package com.country.servcie.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Request class for creating or updating a country.
 * Includes nested states and cities as part of the hierarchy.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountryRequest {
    private String countryCode;
    private String countryName;
    private String officialName;
    private String isdCode;
    private String continent;
    private String currencyCode;
    private String currencyName;
    private String capital;
    private Long population;
    private Double areaSqKm;
    private String officialLanguage;
    private String timeZones;
    private String status;
    private List<StateRequest> states;

}
