package com.compass.msclient.service;

import com.compass.msclient.domain.dto.ClientDto;
import com.compass.msclient.domain.dto.form.ClientFormDto;
import com.compass.msclient.domain.dto.form.ClientUpdateNameFormDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientFormDto body);

    ClientDto find(Long id);

    List<ClientDto> findByName(String name);

    ClientDto updateName(Long id, ClientUpdateNameFormDto body);

    void delete(Long id);

}
