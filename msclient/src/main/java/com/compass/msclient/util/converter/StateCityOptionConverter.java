package com.compass.msclient.util.converter;

import com.compass.msclient.exception.NotFoundAttributeException;
import com.compass.msclient.util.constants.StateCityOption;
import org.springframework.core.convert.converter.Converter;

public class StateCityOptionConverter implements Converter<String, StateCityOption> {

    @Override
    public StateCityOption convert(String state) {
        try {
            return StateCityOption.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundAttributeException("No cities found");
        }
    }
}
