package com.compass.msclient.fixture;

import com.compass.msclient.domain.CityEntity;
import com.compass.msclient.domain.dto.CityDto;
import com.compass.msclient.domain.dto.form.CityFormDto;
import com.compass.msclient.util.constants.StateCityOption;

public class CityFixture {

    public static CityFormDto getCityFormDto() {
        return CityFormDto.builder()
                .name("City test")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static CityDto getCityDto() {
        return CityDto.builder()
                .id(1L)
                .name("City test")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static CityEntity getCityEntity() {
        return CityEntity.builder()
                .id(1L)
                .name("City test")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static CityEntity getCityEntityWithInvalidName() {
        return CityEntity.builder()
                .id(1L)
                .name("A")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static CityFormDto getCityFormDtoWithInvalidAttribute() {
        return CityFormDto.builder()
                .name("A")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

}
