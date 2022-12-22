package com.shop.productcatalogserver.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.productcatalogserver.model.product.option.OptionValue;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeFromIndexedValueContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

public class OptionValueBridge implements ValueBridge<OptionValue, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String toIndexedValue(OptionValue optionValue, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        try {
            return objectMapper.writeValueAsString(optionValue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OptionValue fromIndexedValue(String value, ValueBridgeFromIndexedValueContext context) {
        try {
            return objectMapper.readValue(value, OptionValue.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
