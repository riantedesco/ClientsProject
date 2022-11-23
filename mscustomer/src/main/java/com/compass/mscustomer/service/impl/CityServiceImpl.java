package com.compass.mscustomer.service.impl;

import com.compass.mscustomer.domain.CityEntity;
import com.compass.mscustomer.domain.dto.CityDto;
import com.compass.mscustomer.domain.dto.form.CityFormDto;
import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.repository.CityRepository;
import com.compass.mscustomer.service.CityService;
import com.compass.mscustomer.util.constants.StateCityOption;
import com.compass.mscustomer.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public CityDto save(CityFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		CityEntity city = mapper.map(body, CityEntity.class);
		validation.validateSaveCity(city);
		CityEntity response = this.cityRepository.save(city);
        return mapper.map(response, CityDto.class);
	}

	@Override
	public List<CityDto> findByName(String name) {
		List<CityDto> cities = this.cityRepository.findByName(name).stream().map(c -> mapper.map(c, CityDto.class))
				.collect(Collectors.toList());
		if (cities.isEmpty()) {
			throw new NotFoundAttributeException("No cities found");
		}
		return cities;
	}

	@Override
	public List<CityDto> findByState(StateCityOption state) {
		List<CityDto> cities = this.cityRepository.findByState(state).stream().map(c -> mapper.map(c, CityDto.class))
				.collect(Collectors.toList());
		if (cities.isEmpty()) {
			throw new NotFoundAttributeException("No cities found");
		}
		return cities;
	}

}
