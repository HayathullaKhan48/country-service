package com.country.servcie.response;

import com.country.servcie.request.StateRequest;
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
public class CountryResponse {
    private Long countryId;
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
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<StateResponse> states;
}
