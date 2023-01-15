package com.java.ticketreservations.service.impl;

import com.java.ticketreservations.common.Commons;
import com.java.ticketreservations.common.Constants;
import com.java.ticketreservations.dto.categories.CategoriesRequestDto;
import com.java.ticketreservations.dto.categories.CategoriesResponseDto;
import com.java.ticketreservations.model.Categories;
import com.java.ticketreservations.repository.CategoriesRepository;
import com.java.ticketreservations.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public CategoriesResponseDto create(CategoriesRequestDto requestDto) throws Exception {
        this.checkCategoryCode(requestDto.getCategoryCode(), requestDto.getCategoryId());
        Categories categories = buildModelFromRequest(Constants.CREATE
                , requestDto.getCategoryId(), requestDto);
        CategoriesResponseDto responseDto = buildResponseFromModel(categories);
        return responseDto;
    }

    @Override
    public CategoriesResponseDto update(String categoriesId, CategoriesRequestDto requestDto) throws Exception {
        this.checkCategoryId(categoriesId);
        this.checkCategoryCode(requestDto.getCategoryCode(), categoriesId);
        Categories categories = buildModelFromRequest(Constants.UPDATE, categoriesId, requestDto);
        CategoriesResponseDto responseDto = buildResponseFromModel(categories);
        return responseDto;
    }

    @Override
    public List<CategoriesResponseDto> list() {
        List<Categories> categoriesList = categoriesRepository.findAll();
        return getListOfCategories(categoriesList);
    }

    @Override
    public String delete(String categoriesId) throws Exception {
        String response = "Delete Category Success!";
        this.checkCategoryId(categoriesId);
        categoriesRepository.softDeleteProcess(categoriesId);
        return response;
    }

    @Override
    public CategoriesResponseDto view(String categoriesId) throws Exception {
        this.checkCategoryId(categoriesId);
        CategoriesResponseDto responseDto = getViewCategory(categoriesId);
        return responseDto;
    }

    private CategoriesResponseDto getViewCategory(String categoriesId) {
        List<Categories> categoriesList = categoriesRepository.findAll();
        CategoriesResponseDto responseDto = null;
        for (Categories categories : categoriesList) {
            if (categories.getCategoryId().equals(categoriesId)) {
                responseDto = buildResponseFromModel(categories);
            }
        }
        return responseDto;
    }

    private List<CategoriesResponseDto> getListOfCategories(List<Categories> categoriesList) {
        List<CategoriesResponseDto> responseDtoList = new ArrayList<>();
        for (Categories categories : categoriesList) {
            if (categories.getDeleteStatus() == 0) {
                CategoriesResponseDto responseDto = CategoriesResponseDto.builder()
                        .categoryCode(categories.getCategoryCode())
                        .categoryName(categories.getCategoryName())
                        .categoryDescription(categories.getCategoryDescription())
                        .activeStatus(categories.getActiveStatus())
                        .createdDate(categories.getCreatedDate())
                        .createdBy(categories.getCreatedBy())
                        .lastUpdatedDate(categories.getLastUpdatedDate())
                        .lastUpdatedBy(categories.getLastUpdatedBy())
                        .build();
                responseDtoList.add(responseDto);
            }
        }
        return responseDtoList;
    }

    private void checkCategoryId(String categoryId) throws Exception {
        if (categoriesRepository.findById(categoryId).isEmpty()) {
            throw new Exception("Categories Id Not Found!");
        } else if (!categoriesRepository.findById(categoryId).isEmpty()
                && categoriesRepository.findById(categoryId).get().getDeleteStatus() != 0) {
            throw new Exception("Category has been deleted!");
        }
    }

    private void checkCategoryCode(String categoryCode, String categoryId) throws Exception {
        List<Categories> categoriesList = categoriesRepository.findAll();
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCategoryCode().equalsIgnoreCase(categoryCode)
                    && !categoriesList.get(i).getCategoryId().equalsIgnoreCase(categoryId)) {
                throw new Exception("Category Code Already Exists!");
            } else if (categoryCode.isEmpty() || categoryCode.equals("")){
                throw new Exception("Category code cannot be empty!");
            }
        }
    }

    private CategoriesResponseDto buildResponseFromModel(Categories categories) {
        CategoriesResponseDto responseDto = CategoriesResponseDto.builder()
                .categoryCode(categories.getCategoryCode())
                .categoryName(categories.getCategoryName())
                .categoryDescription(categories.getCategoryDescription())
                .activeStatus(categories.getActiveStatus())
                .createdBy(categories.getCreatedBy())
                .createdDate(categories.getCreatedDate())
                .lastUpdatedBy(categories.getLastUpdatedBy())
                .lastUpdatedDate(categories.getLastUpdatedDate())
                .build();
        return responseDto;
    }

    private Categories buildModelFromRequest(String MODE_TYPE, String categoryId
            , CategoriesRequestDto requestDto) throws Exception {
        Categories categories = new Categories();
        if (MODE_TYPE.equals(Constants.CREATE)) {
            String categoriesId = Commons.generateId(categoryId);
            categories.setCategoryId(categoriesId);
            categories.setActiveStatus(Constants.ACTIVE_STATUS);
            categories.setCreatedBy(requestDto.getCreatedBy());
            categories.setCreatedDate(Commons.getToday());
        } else if (MODE_TYPE.equals(Constants.UPDATE)) {
            categories = insertModelForUpdate(categoryId, requestDto);
        }
        categories.setCategoryCode(requestDto.getCategoryCode());
        categories.setCategoryName(requestDto.getCategoryName());
        categories.setCategoryDescription(requestDto.getCategoryDescription());
        categories.setDeleteStatus(0);

        categoriesRepository.save(categories);
        return categories;
    }

    private Categories insertModelForUpdate(String categoriesId, CategoriesRequestDto requestDto) throws Exception {
        Optional<Categories> categoriesOptional = categoriesRepository
                .findById(categoriesId);
        String createdBy = categoriesOptional.get().getCreatedBy();
        LocalDate createdDate = categoriesOptional.get().getCreatedDate();

        Character activeStatus = Commons.checkStatusActive(requestDto.getActiveStatus());

        Categories categories = Categories.builder()
                .categoryId(categoriesId)
                .activeStatus(activeStatus)
                .createdBy(createdBy)
                .createdDate(createdDate)
                .lastUpdatedBy(requestDto.getLastUpdatedBy())
                .lastUpdatedDate(Commons.getToday())
                .build();
        return categories;
    }
}
