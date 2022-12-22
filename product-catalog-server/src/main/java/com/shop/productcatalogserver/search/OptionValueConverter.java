package com.shop.productcatalogserver.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.productcatalogserver.model.product.option.OptionValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class OptionValueConverter implements Converter<String, OptionValue> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OptionValue convert(@NonNull String source) {
        try {
            return objectMapper.readValue(source, OptionValue.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
