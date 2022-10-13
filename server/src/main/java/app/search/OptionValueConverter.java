package app.search;

import app.model.product.option.OptionValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
