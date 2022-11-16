package com.compass.msclient.service;

import com.compass.msclient.domain.CityEntity;
import com.compass.msclient.domain.dto.CityDto;
import com.compass.msclient.exception.NotFoundAttributeException;
import com.compass.msclient.fixture.CityFixture;
import com.compass.msclient.repository.CityRepository;
import com.compass.msclient.service.impl.CityServiceImpl;
import com.compass.msclient.util.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCity_WhenSendSaveCityValid_ExpectedCity ()  {
        when(cityRepository.save(any(CityEntity.class))).thenReturn(CityFixture.getCityEntity());
        CityDto response = cityService.save(CityFixture.getCityFormDto());

        verify(cityRepository, times(1)).save(any(CityEntity.class));
        assertEquals(response.getId(), CityFixture.getCityDto().getId());
        assertNotNull(response);
    }

    @Test
    void findByNameCity_WhenSendFindByNameCityWithNameValid_ExpectedCity ()  {
        when(cityRepository.findByName(anyString())).thenReturn(List.of(CityFixture.getCityEntity()));
        List<CityDto> response = cityService.findByName(CityFixture.getCityDto().getName());

        assertFalse(response.isEmpty());
    }

    @Test
    void findByNameCity_WhenSendFindByNameCityWithNameInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> cityService.findByName("Name blá blá blá"));

        assertNotNull(response);
        assertEquals("No cities found", response.getMessage());
    }

    @Test
    void findByStateCity_WhenSendFindByStateCityWithStateValid_ExpectedCity ()  {
        when(cityRepository.findByState(any())).thenReturn(List.of(CityFixture.getCityEntity()));
        List<CityDto> response = cityService.findByState(CityFixture.getCityDto().getState());

        assertFalse(response.isEmpty());
    }

    @Test
    void findByStateCity_WhenSendFindByStateCityWithStateInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> cityService.findByState(any()));

        assertNotNull(response);
        assertEquals("No cities found", response.getMessage());
    }

}
