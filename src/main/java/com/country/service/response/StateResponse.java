package com.country.service.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StateResponse {
    private Long stateId;
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
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<CityResponse> cities;
}
