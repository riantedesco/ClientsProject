package com.compass.msclient.service.impl;

import com.compass.msclient.domain.CityEntity;
import com.compass.msclient.domain.ClientEntity;
import com.compass.msclient.domain.dto.CityDto;
import com.compass.msclient.domain.dto.ClientDto;
import com.compass.msclient.domain.dto.form.ClientFormDto;
import com.compass.msclient.domain.dto.form.ClientUpdateNameFormDto;
import com.compass.msclient.exception.InvalidAttributeException;
import com.compass.msclient.exception.NotFoundAttributeException;
import com.compass.msclient.repository.CityRepository;
import com.compass.msclient.repository.ClientRepository;
import com.compass.msclient.service.ClientService;
import com.compass.msclient.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public ClientDto save(ClientFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		ClientEntity client = mapper.map(body, ClientEntity.class);
		client.setId(null);

		if (body.getIdCity() != null) {
			Optional<CityEntity> city = this.cityRepository.findById(body.getIdCity());
			if(!city.isPresent()) {
				throw new InvalidAttributeException("City not found");
			}
			client.setCity(city.get());
		}

		validation.validateClient(client);
		ClientEntity response = this.clientRepository.save(client);
		return mapper.map(response, ClientDto.class);
	}

	@Override
	public ClientDto find(Long id) {
		Optional<ClientEntity> client = this.clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new NotFoundAttributeException("Client not found");
		}
		return mapper.map(client.get(), ClientDto.class);
	}

	@Override
	public List<ClientDto> findByName(String name) {
		List<ClientDto> clients = this.clientRepository.findByName(name).stream().map(c -> mapper.map(c, ClientDto.class))
				.collect(Collectors.toList());
		if (clients.isEmpty()) {
			throw new NotFoundAttributeException("No clients found");
		}
		return clients;
	}

	@Override
	public ClientDto updateName(Long id, ClientUpdateNameFormDto body) {
		Optional<ClientEntity> client = this.clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new NotFoundAttributeException("Client not found");
		}
		client.get().setName(body.getName());

		validation.validateClient(client.get());
		ClientEntity response = this.clientRepository.save(client.get());
		return mapper.map(response, ClientDto.class);
	}

	@Override
	public void delete(Long id) {
		Optional<ClientEntity> client = this.clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new NotFoundAttributeException("Client not found");
		}
		this.clientRepository.deleteById(id);
	}

}
