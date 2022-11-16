package com.compass.msclient.controller;

import com.compass.msclient.domain.dto.CityDto;
import com.compass.msclient.domain.dto.form.CityFormDto;
import com.compass.msclient.service.CityService;
import com.compass.msclient.util.constants.StateCityOption;
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
@RequestMapping("/v1/city")
public class CityController {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "Cadastra uma cidade")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna a cidade recém cadastrada"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<CityDto> save(@RequestBody @Valid CityFormDto body) {
		return new ResponseEntity<>(this.cityService.save(body), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Busca uma cidade pelo nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a cidade encontrada"),
			@ApiResponse(code = 404, message = "Cidade não encontrada")})
	@GetMapping(value = "/findByName", produces = "application/json")
	public ResponseEntity<List<CityDto>> findByName(@RequestParam String name) {
		return ResponseEntity.ok(this.cityService.findByName(name));
	}

	@ApiOperation(value = "Busca uma cidade pelo estado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a cidade encontrada"),
			@ApiResponse(code = 404, message = "Cidade não encontrada")})
	@GetMapping(value = "/findByState", produces = "application/json")
	public ResponseEntity<List<CityDto>> findByState(@RequestParam StateCityOption state) {
		return ResponseEntity.ok(this.cityService.findByState(state));
	}

}
