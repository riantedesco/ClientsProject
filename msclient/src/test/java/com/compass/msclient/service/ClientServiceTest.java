package com.compass.msclient.service;

import com.compass.msclient.domain.ClientEntity;
import com.compass.msclient.domain.dto.ClientDto;
import com.compass.msclient.exception.NotFoundAttributeException;
import com.compass.msclient.fixture.CityFixture;
import com.compass.msclient.fixture.ClientFixture;
import com.compass.msclient.repository.CityRepository;
import com.compass.msclient.repository.ClientRepository;
import com.compass.msclient.service.impl.ClientServiceImpl;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CityRepository cityRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClient_WhenSendSaveClientValid_ExpectedClient ()  {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(CityFixture.getCityEntity()));

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(ClientFixture.getClientEntity());
        ClientDto response = clientService.save(ClientFixture.getClientFormDto());

        verify(clientRepository, times(1)).save(any(ClientEntity.class));
        assertEquals(response.getId(), ClientFixture.getClientEntity().getId());
        assertNotNull(response);
    }

    @Test
    void findClient_WhenSendFindClientWithIdValid_ExpectedClient ()  {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity().getCity()));

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        ClientDto response = clientService.find(ClientFixture.getClientEntity().getId());

        assertNotNull(response);
    }

    @Test
    void findClient_WhenSendFindClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> clientService.find(5000L));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

    @Test
    void findByNameClient_WhenSendFindByNameClientWithNameValid_ExpectedClient ()  {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity().getCity()));

        when(clientRepository.findByName(anyString())).thenReturn(List.of(ClientFixture.getClientEntity()));
        List<ClientDto> response = clientService.findByName(ClientFixture.getClientEntity().getName());

        assertNotNull(response);
    }

    @Test
    void findByNameClient_WhenSendFindByNameClientWithNameInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> clientService.findByName("Nome blá blá blá"));

        assertNotNull(response);
        assertEquals("No clients found", response.getMessage());
    }

    @Test
    void updateNameClient_WhenSendUpdateNameClientWithIdValid_ExpectedClient ()  {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(ClientFixture.getClientEntity());
        ClientDto response = clientService.updateName(ClientFixture.getClientEntity().getId(), ClientFixture.getClientUpdateNameFormDto());

        assertEquals(ClientFixture.getClientDto().getId(), response.getId());
        assertNotNull(response);
    }

    @Test
    void updateNameClient_WhenSendUpdateNameClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> clientService.updateName(5000L, ClientFixture.getClientUpdateNameFormDto()));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

    @Test
    void deleteClient_WhenSendDeleteClientWithIdValid_ExpectedOk ()  {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(ClientFixture.getClientEntity()));
        doNothing().when(clientRepository).deleteById(anyLong());
        clientService.delete(ClientFixture.getClientEntity().getId());

        verify(clientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteClient_WhenSendDeleteClientWithIdInvalid_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> clientService.delete(5000L));

        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

}
