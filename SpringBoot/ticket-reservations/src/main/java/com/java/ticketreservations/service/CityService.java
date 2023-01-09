package com.java.ticketreservations.service;

import com.java.ticketreservations.dto.city.CityRequestDto;
import com.java.ticketreservations.dto.city.CityResponseDto;

import java.util.List;

public interface CityService {
    public CityResponseDto create (CityRequestDto requestDto) throws Exception;

    public List<CityResponseDto> list ();

    public CityResponseDto update (CityRequestDto requestDto) throws Exception;

    public String delete (String cityId) throws Exception;

    public List<CityResponseDto> findCityByName(String name);
}
