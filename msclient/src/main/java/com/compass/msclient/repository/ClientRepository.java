package com.compass.msclient.repository;

import com.compass.msclient.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT c FROM ClientEntity c WHERE c.name = :name")
    List<ClientEntity> findByName(String name);

}
