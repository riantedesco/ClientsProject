package com.compass.mscustomer.fixture;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateNameFormDto;

import java.time.LocalDate;
import java.util.List;

public class CustomerFixture {

    public static CustomerEntity getCustomerEntity() {
        return CustomerEntity.builder()
                .id(1L)
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .city(CityFixture.getCityEntity())
                .build();
    }

    public static CustomerFormDto getCustomerFormDto() {
        return CustomerFormDto.builder()
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .idCity(CityFixture.getCityEntity().getId())
                .build();
    }

    public static CustomerDto getCustomerDto() {
        return CustomerDto.builder()
                .id(1L)
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .age(20)
                .city(CityFixture.getCityDto())
                .build();
    }

    public static CustomerEntity getCustomerEntityWithInvalidSex() {
        return CustomerEntity.builder()
                .id(1L)
                .name("Customer default")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .city(CityFixture.getCityEntity())
                .build();
    }

    public static CustomerFormDto getCustomerFormDtoWithInvalidAttribute() {
        return CustomerFormDto.builder()
                .name("Customer default")
                .sex("Masculino")
                .birthdate(LocalDate.parse("2002-03-30"))
                .idCity(5000L)
                .build();
    }

    public static CustomerFormDto getCustomerFormDtoWithInvalidCity() {
        return CustomerFormDto.builder()
                .name("A")
                .sex("Helicóptero")
                .birthdate(LocalDate.parse("2002-03-30"))
                .idCity(5000L)
                .build();
    }

    public static CustomerUpdateNameFormDto getCustomerUpdateNameFormDto() {
        return CustomerUpdateNameFormDto.builder()
                .name("Customer default")
                .build();
    }

    public static CustomerUpdateNameFormDto getCustomerUpdateNameFormDtoWithInvalidName() {
        return CustomerUpdateNameFormDto.builder()
                .name("A")
                .build();
    }

}
