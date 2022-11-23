package com.compass.mscustomer.service.impl;

import com.compass.mscustomer.domain.CityEntity;
import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateNameFormDto;
import com.compass.mscustomer.exception.InvalidAttributeException;
import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.repository.CityRepository;
import com.compass.mscustomer.repository.CustomerRepository;
import com.compass.mscustomer.service.CustomerService;
import com.compass.mscustomer.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public CustomerDto save(CustomerFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		CustomerEntity customer = mapper.map(body, CustomerEntity.class);
		customer.setId(null);

		if (body.getIdCity() != null) {
			Optional<CityEntity> city = this.cityRepository.findById(body.getIdCity());
			if(!city.isPresent()) {
				throw new InvalidAttributeException("City not found");
			}
			customer.setCity(city.get());
		}

		validation.validateSaveCustomer(customer);
		CustomerEntity response = this.customerRepository.save(customer);
		return mapper.map(response, CustomerDto.class);
	}

	@Override
	public CustomerDto find(Long id) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		}
		return mapper.map(customer.get(), CustomerDto.class);
	}

	@Override
	public List<CustomerDto> findByName(String name) {
		List<CustomerDto> customers = this.customerRepository.findByName(name).stream().map(c -> mapper.map(c, CustomerDto.class))
				.collect(Collectors.toList());
		if (customers.isEmpty()) {
			throw new NotFoundAttributeException("No customers found");
		}
		return customers;
	}

	@Override
	public CustomerDto updateName(Long id, CustomerUpdateNameFormDto body) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		}
		customer.get().setName(body.getName());

		validation.validateUpdateCustomer(customer.get());
		CustomerEntity response = this.customerRepository.save(customer.get());
		return mapper.map(response, CustomerDto.class);
	}

	@Override
	public void delete(Long id) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		}
		this.customerRepository.deleteById(id);
	}

}
