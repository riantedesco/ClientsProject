package com.compass.mscustomer.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Retorno dos dados do cliente")
public class CustomerDto {

    @ApiModelProperty(value = "Id do cliente")
    private Long id;

    @ApiModelProperty(value = "Nome do cliente")
    private String name;

    @ApiModelProperty(value = "Sexo do cliente")
    private String sex;

    @ApiModelProperty(value = "Data de nascimento do cliente")
    private LocalDate birthdate;

    @ApiModelProperty(value = "Idade do cliente")
    private Integer age;

    @ApiModelProperty(value = "Cidade do cliente")
    private CityDto city;

}
