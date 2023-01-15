package com.java.ticketreservations.service;

import com.java.ticketreservations.dto.categories.CategoriesRequestDto;
import com.java.ticketreservations.dto.categories.CategoriesResponseDto;

import java.util.List;

public interface CategoriesService {
    public CategoriesResponseDto create(CategoriesRequestDto requestDto) throws Exception;

    public CategoriesResponseDto update(String categoriesId, CategoriesRequestDto requestDto) throws Exception;

    public List<CategoriesResponseDto> list();

    public String delete(String categoriesId) throws Exception;

    public CategoriesResponseDto view(String categoriesId) throws Exception;
}
