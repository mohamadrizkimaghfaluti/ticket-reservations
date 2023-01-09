package com.java.ticketreservations.controller;

import com.java.ticketreservations.dto.city.CityRequestDto;
import com.java.ticketreservations.dto.city.CityResponseDto;
import com.java.ticketreservations.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket-reservations/")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("list")
    public ResponseEntity<List<CityResponseDto>> list () {
        List<CityResponseDto> responseDto = cityService.list();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<CityResponseDto> create (@RequestBody CityRequestDto requestDto) throws Exception {
        CityResponseDto responseDto = cityService.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<CityResponseDto> update (@RequestBody CityRequestDto requestDto) throws Exception {
        CityResponseDto responseDto = cityService.update(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/{cityId}")
    public ResponseEntity<String> delete (@PathVariable("cityId") String cityId) throws Exception {
        String responseDto = cityService.delete(cityId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("searchCityByName/{name}")
    public ResponseEntity<List<CityResponseDto>> findCityByName(@PathVariable("name") String name) {
        List<CityResponseDto> responseDtoList = cityService.findCityByName(name);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

}
