package com.java.ticketreservations.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_cities")
public class City {

    @Id
    @NotNull
    @Column(name = "city_id")
    private String cityId;

    @NotNull
    @Column(name = "city_code", length = 30)
    private String cityCode;

    @NotNull
    @Column(name = "city_name", length = 30)
    private String cityName;

    @NotNull
    @Column(name = "active_status")
    private Character activeStatus;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "last_updated_date")
    private LocalDate lastUpdateDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
}
