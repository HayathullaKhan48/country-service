package com.country.service.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityResponse {
    private Long cityId;
    private String cityCode;
    private String cityName;
    private String cityType;
    private Long cityPopulation;
    private Double cityAreaSqKm;
    private Integer pinCode;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
