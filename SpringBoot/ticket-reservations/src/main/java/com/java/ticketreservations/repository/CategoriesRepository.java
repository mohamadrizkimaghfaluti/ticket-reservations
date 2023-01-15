package com.java.ticketreservations.repository;

import com.java.ticketreservations.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, String> {
    @Modifying
    @Transactional
    @Query(
            value = "update mst_categories set delete_status = -1 where category_id = :categoriesId"
            , nativeQuery = true
    )
    void softDeleteProcess(@Param("categoriesId") String categoriesId);
}
