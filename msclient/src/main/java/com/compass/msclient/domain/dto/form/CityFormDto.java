package com.compass.msclient.domain.dto.form;

import com.compass.msclient.util.constants.StateCityOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityFormDto {

    private String name;

    private StateCityOption state;

}
