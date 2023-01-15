package com.java.ticketreservations.dto.categories;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CategoriesRequestDto {
    private String categoryId;
    private String categoryCode;
    private String categoryName;
    private String categoryDescription;
    private Character activeStatus;
    private Integer deleteStatus;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate lastUpdatedDate;
    private String lastUpdatedBy;
}
