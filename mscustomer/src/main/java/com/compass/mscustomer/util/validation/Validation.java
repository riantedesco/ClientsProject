package com.compass.mscustomer.util.validation;

import com.compass.mscustomer.domain.CityEntity;
import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.util.validation.save.CustomerSaveValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Validation {

	@Autowired
	private CustomerSaveValidation customerSaveValidation;

	public void validateSaveCustomer (CustomerEntity client) {
		customerSaveValidation.validateName(client);
		customerSaveValidation.validateSex(client);
		customerSaveValidation.validateBirthdate(client);
		customerSaveValidation.validateAge(client);
		customerSaveValidation.validateCity(client);
	}

	public void validateSaveCity (CityEntity city) {
	}

	public void validateUpdateCustomer(CustomerEntity client) {
	}

}
