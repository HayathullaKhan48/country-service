package com.country.service.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Request class for City details.
 * Part of StateRequest for nested data submission
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityRequest {
    private String cityCode;
    private String cityName;
    private String cityType;
    private Long cityPopulation;
    private Double cityAreaSqKm;
    private Integer pinCode;
    private String status;

}
