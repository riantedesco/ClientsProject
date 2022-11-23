package com.compass.mscustomer.util.validation.save;

import com.compass.mscustomer.exception.InvalidAttributeException;
import com.compass.mscustomer.fixture.CustomerFixture;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class CustomerSaveValidationTest {

    @InjectMocks
    private CustomerSaveValidation customerSaveValidation;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateSex_WhenSendInvalidSex_ExpectedInvalidAttributeException ()  {
        InvalidAttributeException response = assertThrows(InvalidAttributeException.class, () ->
                customerSaveValidation.validateSex(CustomerFixture.getCustomerEntityWithInvalidSex()));
        assertNotNull(response);
        assertEquals("Invalid sex", response.getMessage());
    }

}
