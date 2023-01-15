package com.java.ticketreservations.controller;

import com.java.ticketreservations.dto.categories.CategoriesRequestDto;
import com.java.ticketreservations.dto.categories.CategoriesResponseDto;
import com.java.ticketreservations.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket-reservations/categories/")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("create")
    public ResponseEntity<CategoriesResponseDto> create(@RequestBody CategoriesRequestDto requestDto)
            throws Exception {
        CategoriesResponseDto responseDto = categoriesService.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<List<CategoriesResponseDto>> list() {
        List<CategoriesResponseDto> responseDtoList = categoriesService.list();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping("update/{categoriesId}")
    public ResponseEntity<CategoriesResponseDto> update(@PathVariable("categoriesId") String categoriesId
            , @RequestBody CategoriesRequestDto requestDto) throws Exception {
        CategoriesResponseDto responseDto = categoriesService.update(categoriesId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("delete/{categoriesId}")
    public ResponseEntity<String> delete(@PathVariable("categoriesId") String categoriesId)
            throws Exception {
        String response = categoriesService.delete(categoriesId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("view/{categoriesId}")
    public ResponseEntity<CategoriesResponseDto> view(@PathVariable(
            "categoriesId") String categoriesId) throws Exception {
        CategoriesResponseDto responseDto = categoriesService.view(categoriesId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
