package com.compass.mscustomer.domain.dto.form;

import com.compass.mscustomer.util.constants.StateCityOption;
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
@ApiModel(value = "Envio dos dados da cidade")
public class CityFormDto {

    @ApiModelProperty(value = "Nome da cidade")
    @NotNull
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;

    @ApiModelProperty(value = "Estado da cidade")
    @NotNull
    private StateCityOption state;

}
