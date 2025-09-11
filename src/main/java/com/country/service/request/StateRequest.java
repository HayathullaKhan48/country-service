package com.country.service.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Request class for State details.
 * Part of CountryRequest to send hierarchical data.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StateRequest {
    private String stateCode;
    private String stateName;
    private String stateType;
    private String stateCapital;
    private Long statePopulation;
    private Double stateAreaSqKm;
    private String officialLanguages;
    private String timeZone;
    private String gstCode;
    private String status;
    private List<CityRequest> cities;
}
