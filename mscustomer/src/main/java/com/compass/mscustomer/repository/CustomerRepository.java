package com.compass.mscustomer.repository;

import com.compass.mscustomer.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM CustomerEntity c WHERE c.name = :name")
    List<CustomerEntity> findByName(String name);

}
