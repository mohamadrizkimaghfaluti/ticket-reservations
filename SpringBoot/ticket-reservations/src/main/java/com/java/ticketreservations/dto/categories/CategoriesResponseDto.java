package com.java.ticketreservations.dto.categories;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CategoriesResponseDto {
    private String categoryCode;
    private String categoryName;
    private String categoryDescription;
    private Character activeStatus;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate lastUpdatedDate;
    private String lastUpdatedBy;
}
