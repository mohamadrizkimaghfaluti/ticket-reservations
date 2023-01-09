package com.java.ticketreservations.dto.city;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityRequestDto {
    private String cityId;
    private String cityCode;
    private String cityName;
    private Character activeStatus;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate lastUpdateDate;
    private String lastUpdatedBy;
}
