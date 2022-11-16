package com.compass.msclient.service;

import com.compass.msclient.domain.dto.CityDto;
import com.compass.msclient.domain.dto.form.CityFormDto;
import com.compass.msclient.util.constants.StateCityOption;

import java.util.List;

public interface CityService {

    CityDto save(CityFormDto body);

    List<CityDto> findByName(String name);

    List<CityDto> findByState(StateCityOption state);

}
