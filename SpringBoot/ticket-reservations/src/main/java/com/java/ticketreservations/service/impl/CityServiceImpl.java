package com.java.ticketreservations.service.impl;

import com.java.ticketreservations.common.Commons;
import com.java.ticketreservations.common.Constants;
import com.java.ticketreservations.dto.city.CityRequestDto;
import com.java.ticketreservations.dto.city.CityResponseDto;
import com.java.ticketreservations.model.City;
import com.java.ticketreservations.repository.CityRepository;
import com.java.ticketreservations.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityResponseDto create(CityRequestDto requestDto) throws Exception {
        this.checkCityCode(requestDto.getCityCode(), requestDto.getCityId());
        String cityId = Commons.generateId(requestDto.getCityId());
        CityResponseDto responseDto = buildModelFromRequest(Constants.CREATE
                , cityId, requestDto);
        return responseDto;
    }

    @Override
    public List<CityResponseDto> list() {
        List<City> cityList = cityRepository.findAll();
        return getListOfCities(cityList);
    }

    @Override
    public CityResponseDto update(CityRequestDto requestDto) throws Exception {
        this.checkCityId(requestDto.getCityId());
        this.checkCityCode(requestDto.getCityCode(), requestDto.getCityId());
        CityResponseDto responseDto = buildModelFromRequest(Constants.UPDATE
                , requestDto.getCityId(), requestDto);
        return responseDto;
    }

    @Override
    public String delete(String cityId) throws Exception {
        this.checkCityId(cityId);
        if (!cityId.isEmpty()) {
            cityRepository.deleteById(cityId);
        }
        return "Delete City Success!";
    }

    @Override
    public List<CityResponseDto> findCityByName(String name) {
        List<City> cityList = cityRepository.findCityByName(name);
        return getListOfCities(cityList);
    }

    private List<CityResponseDto> getListOfCities(List<City> cityList) {
        List<CityResponseDto> responseDtoList = new ArrayList<>();
        for (City city: cityList) {
            if (city.getActiveStatus().equals(Constants.ACTIVE_STATUS)) {
                CityResponseDto responseDto = CityResponseDto.builder()
                        .cityCode(city.getCityCode())
                        .cityName(city.getCityName())
                        .activeStatus(city.getActiveStatus())
                        .createdBy(city.getCreatedBy())
                        .createdDate(city.getCreatedDate())
                        .lastUpdatedBy(city.getLastUpdatedBy())
                        .lastUpdateDate(city.getLastUpdateDate())
                        .build();
                responseDtoList.add(responseDto);
            }
        }
        return responseDtoList;
    }

    private void checkCityCode(String cityCode, String cityId) throws Exception {
        List<City> cityList = cityRepository.findAll();
        for (City city : cityList) {
            if (city.getCityCode().equals(cityCode) && !city.getCityId().equals(cityId)) {
                throw new Exception("City Code Already Exists!");
            }
        }
    }

    private void checkCityId(String cityId) throws Exception {
        if (cityRepository.findById(cityId).isEmpty()){
            throw new Exception("City Id Not Found!");
        }
    }

    private CityResponseDto buildModelFromRequest(String MODE_TYPE
            , String cityId, CityRequestDto requestDto) throws Exception {
        City city = new City();
        if (MODE_TYPE.equals(Constants.CREATE)){
            city.setActiveStatus(Constants.ACTIVE_STATUS);
            city.setCreatedBy(requestDto.getCreatedBy());
            city.setCreatedDate(Commons.getToday());
        }
        if (MODE_TYPE.equals(Constants.UPDATE)){
            city = modeUpdateCity(cityId, requestDto);
        }
        city.setCityId(cityId);
        city.setCityCode(requestDto.getCityCode());
        city.setCityName(requestDto.getCityName());
        cityRepository.save(city);
        return buildResponseFromModel(city);
    }

    private City modeUpdateCity(String cityId, CityRequestDto requestDto) throws Exception {
        Optional<City> cityList = cityRepository.findById(cityId);
        String createdBy = cityList.get().getCreatedBy();
        LocalDate createdDate = cityList.get().getCreatedDate();

        City city = new City();
        Character status = Commons.checkStatusActive(requestDto.getActiveStatus());
        city.setActiveStatus(status);
        city.setLastUpdatedBy(requestDto.getLastUpdatedBy());
        city.setLastUpdateDate(Commons.getToday());
        city.setCreatedBy(createdBy);
        city.setCreatedDate(createdDate);
        return city;
    }

    private CityResponseDto buildResponseFromModel(City city) {
        CityResponseDto responseDto = CityResponseDto.builder()
                .cityCode(city.getCityCode())
                .cityName(city.getCityName())
                .activeStatus(city.getActiveStatus())
                .createdBy(city.getCreatedBy())
                .createdDate(city.getCreatedDate())
                .lastUpdatedBy(city.getLastUpdatedBy())
                .lastUpdateDate(city.getLastUpdateDate())
                .build();
        return responseDto;
    }

}
