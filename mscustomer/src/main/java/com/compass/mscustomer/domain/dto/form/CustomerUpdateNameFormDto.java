package com.compass.mscustomer.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados da atualização do cliente")
public class CustomerUpdateNameFormDto {

    @ApiModelProperty(value = "Nome do cliente")
    @NotNull
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;

}
