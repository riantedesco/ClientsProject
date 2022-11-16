package com.compass.msclient.util.validation;

import com.compass.msclient.domain.CityEntity;
import com.compass.msclient.domain.ClientEntity;
import com.compass.msclient.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class Validation {

	public void validateClient (ClientEntity client) {
		//validate name
		long countCharName = client.getName().chars().filter(ch -> ch != ' ').count();
		if (countCharName < 3) {
			throw new InvalidAttributeException("Name must contain at least 3 characters");
		}

		//validate sex
		String masculino = "Masculino";
		String feminino = "Feminino";
		List<String> list = Arrays.asList(masculino, feminino);
		if (!list.contains(client.getSex())) {
			throw new InvalidAttributeException("Invalid sex");
		}

		//validate birthdate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = client.getBirthdate().format(formatter);
		LocalDate localDateBirthdate = LocalDate.parse(dataFormatada, formatter);
		client.setBirthdate(localDateBirthdate);

		//validate age
		client.setAge(Period.between(localDateBirthdate, LocalDate.now()).getYears());
	}

	public void validateCity (CityEntity city) {
		//validate name
		long countCharName = city.getName().chars().filter(ch -> ch != ' ').count();
		if (countCharName < 3) {
			throw new InvalidAttributeException("Name must contain at least 3 characters");
		}
	}

}
