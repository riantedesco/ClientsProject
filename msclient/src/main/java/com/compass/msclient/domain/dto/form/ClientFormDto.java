package com.compass.msclient.domain.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientFormDto {

    private String name;

    private String sex;

    private LocalDate birthdate;

    private Long idCity;

}
