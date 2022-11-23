package com.compass.mscustomer.repository;

import com.compass.mscustomer.domain.CityEntity;
import com.compass.mscustomer.util.constants.StateCityOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT c FROM CityEntity c WHERE c.name = :name")
    List<CityEntity> findByName(String name);

    @Query("SELECT c FROM CityEntity c WHERE c.state = :state")
    List<CityEntity> findByState(StateCityOption state);

}
