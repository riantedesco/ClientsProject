package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.fixture.CityFixture;
import com.compass.mscustomer.fixture.CustomerFixture;
import com.compass.mscustomer.repository.CityRepository;
import com.compass.mscustomer.repository.CustomerRepository;
import com.compass.mscustomer.service.impl.CustomerServiceImpl;
import com.compass.mscustomer.util.validation.Validation;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CityRepository cityRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClient_WhenSendSaveClientValid_ExpectedClient ()  {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(CityFixture.getCityEntity()));

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(CustomerFixture.getCustomerEntity());
        CustomerDto response = customerService.save(CustomerFixture.getCustomerFormDto());

        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
        assertEquals(response.getId(), CustomerFixture.getCustomerEntity().getId());
        assertNotNull(response);
    }

    @Test
    void findClient_WhenSendFindClientWithIdValid_ExpectedClient ()  {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity().getCity()));

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        CustomerDto response = customerService.find(CustomerFixture.getCustomerEntity().getId());

        assertNotNull(response);
    }

    @Test
    void findClient_WhenSendFindClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> customerService.find(5000L));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }

    @Test
    void findByNameClient_WhenSendFindByNameClientWithNameValid_ExpectedClient ()  {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity().getCity()));

        when(customerRepository.findByName(anyString())).thenReturn(List.of(CustomerFixture.getCustomerEntity()));
        List<CustomerDto> response = customerService.findByName(CustomerFixture.getCustomerEntity().getName());

        assertNotNull(response);
    }

    @Test
    void findByNameClient_WhenSendFindByNameClientWithNameInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> customerService.findByName("Nome blá blá blá"));

        assertNotNull(response);
        assertEquals("No customers found", response.getMessage());
    }

    @Test
    void updateNameClient_WhenSendUpdateNameClientWithIdValid_ExpectedClient ()  {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(CustomerFixture.getCustomerEntity());
        CustomerDto response = customerService.updateName(CustomerFixture.getCustomerEntity().getId(), CustomerFixture.getCustomerUpdateNameFormDto());

        assertEquals(CustomerFixture.getCustomerDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void updateNameClient_WhenSendUpdateNameClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> customerService.updateName(5000L, CustomerFixture.getCustomerUpdateNameFormDto()));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }

    @Test
    void deleteClient_WhenSendDeleteClientWithIdValid_ExpectedOk ()  {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerFixture.getCustomerEntity()));
        doNothing().when(customerRepository).deleteById(anyLong());
        customerService.delete(CustomerFixture.getCustomerEntity().getId());

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteClient_WhenSendDeleteClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> customerService.delete(5000L));

        assertNotNull(response);
        assertEquals("Customer not found", response.getMessage());
    }

}
