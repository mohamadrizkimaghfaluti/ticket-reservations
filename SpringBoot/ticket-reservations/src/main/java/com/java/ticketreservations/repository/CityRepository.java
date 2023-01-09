package com.java.ticketreservations.repository;

import com.java.ticketreservations.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    @Query(
            value = "select * from mst_cities where city_name like %:name%"
            , nativeQuery = true
    )
    List<City> findCityByName(@Param("name") String name);
}
