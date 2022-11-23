package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.dto.CityDto;
import com.compass.mscustomer.domain.dto.form.CityFormDto;
import com.compass.mscustomer.util.constants.StateCityOption;

import java.util.List;

public interface CityService {

    CityDto save(CityFormDto body);

    List<CityDto> findByName(String name);

    List<CityDto> findByState(StateCityOption state);

}
