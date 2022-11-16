package com.compass.msclient.domain.dto;

import com.compass.msclient.util.constants.StateCityOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDto {

    private Long id;

    private String name;

    private StateCityOption state;

}
