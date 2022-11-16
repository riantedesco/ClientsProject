package com.compass.msclient.repository;

import com.compass.msclient.domain.CityEntity;
import com.compass.msclient.util.constants.StateCityOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query("SELECT c FROM CityEntity c WHERE c.name = :name")
    List<CityEntity> findByName(String name);

    @Query("SELECT c FROM CityEntity c WHERE c.state = :state")
    List<CityEntity> findByState(StateCityOption state);

}
