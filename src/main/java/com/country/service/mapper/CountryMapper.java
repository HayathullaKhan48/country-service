package com.country.service.mapper;

import com.country.service.entity.CityModel;
import com.country.service.entity.CountryModel;
import com.country.service.entity.StateModel;
import com.country.service.enums.Status;
import com.country.service.request.CityRequest;
import com.country.service.request.CountryRequest;
import com.country.service.request.StateRequest;
import com.country.service.response.CityResponse;
import com.country.service.response.CountryResponse;
import com.country.service.response.StateResponse;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {

    public static CountryModel toCountryModel(CountryRequest request){
        return CountryModel.builder()
                .countryCode(request.getCountryCode())
                .countryName(request.getCountryName())
                .officialName(request.getOfficialName())
                .isdCode(request.getIsdCode())
                .continent(request.getContinent())
                .currencyCode(request.getCurrencyCode())
                .currencyName(request.getCurrencyName())
                .capital(request.getCapital())
                .population(request.getPopulation())
                .areaSqKm(request.getAreaSqKm())
                .officialLanguage(request.getOfficialLanguage())
                .timeZones(request.getTimeZones())
                .status(Status.valueOf(request.getStatus().toUpperCase()))
                .build();
    }

    public static StateModel requestToStateMapper(CountryModel countryModel, StateRequest request){
        return StateModel.builder()
                .stateCode(request.getStateCode())
                .stateName(request.getStateName())
                .stateType(request.getStateType())
                .stateCapital(request.getStateCapital())
                .statePopulation(request.getStatePopulation())
                .stateAreaSqKm(request.getStateAreaSqKm())
                .officialLanguages(request.getOfficialLanguages())
                .timezone(request.getTimeZone())
                .gstCode(request.getGstCode())
                .status(Status.valueOf(request.getStatus().toUpperCase()))
                .country(countryModel)
                .build();
    }

    public static CityModel requestToCitiesMapper(StateModel stateModel, CityRequest request){
        return CityModel.builder()
                .cityCode(request.getCityCode())
                .cityName(request.getCityName())
                .cityType(request.getCityType())
                .cityPopulation(request.getCityPopulation())
                .cityAreaSqKm(request.getCityAreaSqKm())
                .pinCode(request.getPinCode())
                .status(Status.valueOf(request.getStatus().toUpperCase()))
                .state(stateModel)
                .build();
    }

    public static CountryResponse toCountryResponse( CountryModel model){
        return CountryResponse.builder()
                .countryId(model.getCountryId())
                .countryCode(model.getCountryCode())
                .countryName(model.getCountryName())
                .officialName(model.getOfficialName())
                .isdCode(model.getIsdCode())
                .continent(model.getContinent())
                .currencyCode(model.getCurrencyCode())
                .currencyName(model.getCurrencyName())
                .capital(model.getCapital())
                .population(model.getPopulation())
                .areaSqKm(model.getAreaSqKm())
                .officialLanguage(model.getOfficialLanguage())
                .timeZones(model.getTimeZones())
                .status(model.getStatus().name())
                .createdDate(model.getCreatedDate())
                .updatedDate(model.getUpdatedDate())
                .states(modelToStatesResponse(model.getStates()))
                .build();
    }

    public static List<StateResponse> modelToStatesResponse(List<StateModel> stateModels){
        List<StateResponse> responseList = new ArrayList<>();
        stateModels.forEach(state -> responseList.add(
                StateResponse.builder()
                        .stateId(state.getStateId())
                        .stateCode(state.getStateCode())
                        .stateType(state.getStateType())
                        .stateName(state.getStateName())
                        .stateCapital(state.getStateCapital())
                        .statePopulation(state.getStatePopulation())
                        .stateAreaSqKm(state.getStateAreaSqKm())
                        .officialLanguages(state.getOfficialLanguages())
                        .timeZone(state.getTimezone())
                        .gstCode(state.getGstCode())
                        .status(state.getStatus().name())
                        .createdDate(state.getCreatedDate())
                        .updatedDate(state.getUpdatedDate())
                        .cities(modelToCitiesResponse(state.getCities()))
                        .build()
                )
        );
        return responseList;
    }

    public static List<CityResponse> modelToCitiesResponse(List<CityModel> cityModels){
        List<CityResponse> responseList = new ArrayList<>();
        cityModels.forEach(city -> responseList.add(
                CityResponse.builder()
                        .cityId(city.getCityId())
                        .cityCode(city.getCityCode())
                        .cityName(city.getCityName())
                        .cityType(city.getCityType())
                        .cityPopulation(city.getCityPopulation())
                        .cityAreaSqKm(city.getCityAreaSqKm())
                        .pinCode(city.getPinCode())
                        .status(city.getStatus().name())
                        .createdDate(city.getCreatedDate())
                        .updatedDate(city.getUpdatedDate())
                        .build()
                )
        );
        return responseList;
    }
}
