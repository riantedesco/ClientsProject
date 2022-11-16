package com.compass.msclient.fixture;

import com.compass.msclient.domain.ClientEntity;
import com.compass.msclient.domain.dto.ClientDto;
import com.compass.msclient.domain.dto.form.ClientFormDto;
import com.compass.msclient.domain.dto.form.ClientUpdateNameFormDto;

import java.time.LocalDate;

public class ClientFixture {

    public static ClientEntity getClientEntity() {
        return ClientEntity.builder()
                .id(1L)
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .city(CityFixture.getCityEntity())
                .build();
    }

    public static ClientFormDto getClientFormDto() {
        return ClientFormDto.builder()
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .idCity(CityFixture.getCityEntity().getId())
                .build();
    }

    public static ClientDto getClientDto() {
        return ClientDto.builder()
                .id(1L)
                .name("Client default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .age(20)
                .city(CityFixture.getCityDto())
                .build();
    }

    public static ClientEntity getClientEntityWithInvalidName() {
        return ClientEntity.builder()
                .id(1L)
                .name("A")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .city(CityFixture.getCityEntity())
                .build();
    }

    public static ClientEntity getClientEntityWithInvalidSex() {
        return ClientEntity.builder()
                .id(1L)
                .name("Client default")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .city(CityFixture.getCityEntity())
                .build();
    }

    public static ClientFormDto getClientFormDtoWithInvalidAttribute() {
        return ClientFormDto.builder()
                .name("A")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .idCity(5000L)
                .build();
    }

    public static ClientUpdateNameFormDto getClientUpdateNameFormDto() {
        return ClientUpdateNameFormDto.builder()
                .name("Client default")
                .build();
    }

    public static ClientUpdateNameFormDto getClientUpdateNameFormDtoWithInvalidName() {
        return ClientUpdateNameFormDto.builder()
                .name("A")
                .build();
    }

}
