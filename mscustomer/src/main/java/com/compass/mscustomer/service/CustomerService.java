package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateNameFormDto;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerFormDto body);

    CustomerDto find(Long id);

    List<CustomerDto> findByName(String name);

    CustomerDto updateName(Long id, CustomerUpdateNameFormDto body);

    void delete(Long id);

}
