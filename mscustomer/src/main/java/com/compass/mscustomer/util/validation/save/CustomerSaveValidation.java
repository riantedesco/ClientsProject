package com.compass.mscustomer.util.validation.save;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerSaveValidation {

	public void validateName (CustomerEntity client) {
	}

	public void validateSex (CustomerEntity client) {
		String masculino = "Masculino";
		String feminino = "Feminino";
		List<String> list = Arrays.asList(masculino, feminino);
		if (!list.contains(client.getSex())) {
			throw new InvalidAttributeException("Invalid sex");
		}
	}

	public void validateBirthdate (CustomerEntity client) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = client.getBirthdate().format(formatter);
		LocalDate localDateBirthdate = LocalDate.parse(dataFormatada, formatter);
		client.setBirthdate(localDateBirthdate);
	}

	public void validateAge (CustomerEntity client) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = client.getBirthdate().format(formatter);
		LocalDate localDateBirthdate = LocalDate.parse(dataFormatada, formatter);
		client.setAge(Period.between(localDateBirthdate, LocalDate.now()).getYears());
	}

	public void validateCity (CustomerEntity city) {
	}

}
