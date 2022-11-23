package com.compass.mscustomer.domain.dto.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateNameFormDto {

    @ApiModelProperty(value = "Nome do cliente")
    @NotNull
    private String name;

}
