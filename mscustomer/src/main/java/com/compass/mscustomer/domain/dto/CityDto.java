package com.compass.mscustomer.domain.dto;

import com.compass.mscustomer.util.constants.StateCityOption;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {

    @ApiModelProperty(value = "Id da cidade")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade")
    private String name;

    @ApiModelProperty(value = "Estado da cidade")
    private StateCityOption state;

}
