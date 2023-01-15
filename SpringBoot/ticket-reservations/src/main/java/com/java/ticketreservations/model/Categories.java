package com.java.ticketreservations.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_categories")
public class Categories {
    @Id
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_code", length = 20)
    private String categoryCode;

    @Column(name = "category_name", length = 30)
    private String categoryName;

    @Column(name = "category_description", length = 200)
    private String categoryDescription;

    @Column(name = "active_status")
    private Character activeStatus;

    @Column(name = "delete_status")
    private Integer deleteStatus;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by", length = 30)
    private String createdBy;

    @Column(name = "last_updated_date")
    private LocalDate lastUpdatedDate;

    @Column(name = "last_updated_by", length = 30)
    private String lastUpdatedBy;
}
