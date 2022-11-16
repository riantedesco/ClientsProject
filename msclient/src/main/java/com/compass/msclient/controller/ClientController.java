package com.compass.msclient.controller;

import com.compass.msclient.domain.dto.ClientDto;
import com.compass.msclient.domain.dto.form.ClientFormDto;
import com.compass.msclient.domain.dto.form.ClientUpdateNameFormDto;
import com.compass.msclient.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@ApiOperation(value = "Cadastra um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o cliente recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<ClientDto> save(@RequestBody @Valid ClientFormDto body) {
		return new ResponseEntity<>(this.clientService.save(body), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Busca um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente encontrado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ClientDto> find(@PathVariable Long id) {
		return ResponseEntity.ok(this.clientService.find(id));
	}

	@ApiOperation(value = "Busca um cliente pelo nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente encontrado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@GetMapping(value = "/findByName", produces = "application/json")
	public ResponseEntity<List<ClientDto>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(this.clientService.findByName(name));
	}

	@ApiOperation(value = "Atualiza o nome de um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<ClientDto> updateName(@PathVariable Long id, @RequestBody @Valid ClientUpdateNameFormDto body) {
		return ResponseEntity.ok(this.clientService.updateName(id, body));
	}

	@ApiOperation(value = "Remove um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente deletado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.clientService.delete(id);
		return ResponseEntity.ok().build();
	}

}
