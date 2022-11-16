package com.compass.msclient.util.validation;

import com.compass.msclient.exception.InvalidAttributeException;
import com.compass.msclient.fixture.CityFixture;
import com.compass.msclient.fixture.ClientFixture;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class ValidationTest {

    @InjectMocks
    private Validation validation;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateClient_WhenSendInvalidName_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
            validation.validateClient(ClientFixture.getClientEntityWithInvalidName()));
        assertNotNull(response);
        assertEquals("Name must contain at least 3 characters", response.getMessage());
    }

    @Test
    void validateClient_WhenSendInvalidSex_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                validation.validateClient(ClientFixture.getClientEntityWithInvalidSex()));
        assertNotNull(response);
        assertEquals("Invalid sex", response.getMessage());
    }

    @Test
    void validateCity_WhenSendInvalidName_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = Assertions.assertThrows(InvalidAttributeException.class, () ->
            validation.validateCity(CityFixture.getCityEntityWithInvalidName()));
        assertNotNull(response);
        assertEquals("Name must contain at least 3 characters", response.getMessage());
    }

}
